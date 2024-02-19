package com.freelance.pfe.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.freelance.pfe.message.response.JwtResponse;

import com.freelance.pfe.message.request.LoginForm;
import com.freelance.pfe.message.request.SignUpForm;
import com.freelance.pfe.message.response.JwtResponse;
import com.freelance.pfe.message.response.ResponseMessage;
import com.freelance.pfe.model.Role;
import com.freelance.pfe.model.RoleName;
import com.freelance.pfe.model.User;
import com.freelance.pfe.repository.RoleRepository;
import com.freelance.pfe.repository.UserRepository;
import com.freelance.pfe.security.jwt.JwtProvider;
import com.freelance.pfe.security.services.UserDetailsServiceImpl;
import com.freelance.pfe.security.services.UserPrinciple;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
        
        @Autowired
	JwtProvider jwtUtils;
        
        //Logger logger = LoggerFactory.getLogger();

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
            
            Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

    String jwt = jwtUtils.generateJwtToken(authentication);

    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(Collectors.toList());


    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),userDetails.getUsername(),
            userDetails.getNom(),userDetails.getPrenom(),userDetails.getEmail(), roles,userDetails.getTelephone(),
    userDetails.getSkills(),userDetails.getCv(),userDetails.getAdresse(),userDetails.getPhoto(),userDetails.getDate_nais(),
    userDetails.getAboutme(),userDetails.getCin()));
/*
                    Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();		
		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
           
            Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getRoles()));
                */
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getNom(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		String strRoles = signUpRequest.getRole();
		System.out.println(signUpRequest.getRole());
		Set<Role> roles = new HashSet<>();
		
		/*
		Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(adminRole);
		
		
		*/
		/*
			if(strRoles == "admin")
				{
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(adminRole);

				}
                                
			if(strRoles == "freelancer")
			{
				Role freelancerRole = roleRepository.findByName(RoleName.ROLE_FREELANCER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(freelancerRole);

			}   
				if(strRoles == "user")
				{
				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(userRole);
			}
				*/
		
		switch (strRoles) {
		case "admin":
			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(adminRole);

			break;
                            
        case "freelancer":
			Role freelancerRole = roleRepository.findByName(RoleName.ROLE_FREELANCER)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(freelancerRole);

			break;
		default:
			Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(userRole);
		}
		
		
		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage(strRoles), HttpStatus.OK);
	}
}
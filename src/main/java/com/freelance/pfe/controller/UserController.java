/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freelance.pfe.controller;

import com.freelance.pfe.exception.ResourceNotFoundException;
import com.freelance.pfe.model.Email;
import com.freelance.pfe.model.Role;
import com.freelance.pfe.model.User;
import com.freelance.pfe.repository.UserRepository;
import com.freelance.pfe.security.services.EmailService;
import com.freelance.pfe.security.services.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ya39o
 */

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class UserController {
     @Autowired
    private UserRepository UserRepository;
     
     @Autowired
 	PasswordEncoder encoder;
     
     @Autowired
 	private UserService userService;
     
     @Autowired
     private EmailService emailService;

     public Email emails = new Email();
        
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return  (List<User>) UserRepository.findAll();
        //return (List<User>) UserRepository.findAll();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") String username)
        throws ResourceNotFoundException {
        User users = UserRepository.findByUsername(username)
          .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + username));
        return ResponseEntity.ok().body(users);
    }
    
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User users ) {
    	users.setPassword(encoder.encode(users.getPassword()));
    	users.setToken(userService.generateToken());
    	Set<Role> strRoles = users.getRoles();
		System.out.println(users.getRoles());
		UserRepository.save(users);
        return users;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long username,
         @Valid @RequestBody User usersDetails) throws ResourceNotFoundException {
        User users = UserRepository.findById(username)
        .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + username));

        users.setEmail(usersDetails.getEmail());
        users.setPrenom(usersDetails.getPrenom());
        users.setCv(usersDetails.getCv());
        users.setAdresse(usersDetails.getAdresse());
        users.setSkills(usersDetails.getSkills());
        users.setCin(usersDetails.getCin());
        users.setTelephone(usersDetails.getTelephone());
        users.setAboutme(usersDetails.getAboutme());
        users.setUsername(usersDetails.getUsername());
        users.setNom(usersDetails.getNom());
        users.setEtat(usersDetails.getEtat());
        users.setPhoto(usersDetails.getPhoto());
        final User updatedUser = UserRepository.save(users);
        return ResponseEntity.ok(updatedUser);
    }
    
    @PutMapping("/users/photo/{id}")
    public ResponseEntity<User> updateUserphoto(@PathVariable(value = "id") Long username,
         @Valid @RequestBody User usersDetails) throws ResourceNotFoundException {
        User users = UserRepository.findById(username)
        .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + username));

        users.setPhoto(usersDetails.getPhoto());
        final User updatedUser = UserRepository.save(users);
        return ResponseEntity.ok(updatedUser);
    }
    
    
    @PutMapping("/users/block/{id}")
    public ResponseEntity<User> blockuser(@PathVariable(value = "id") Long username,
         @Valid @RequestBody User usersDetails) throws ResourceNotFoundException {
        User users = UserRepository.findById(username)
        .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + username));

        users.setEtat("bloquer");
        final User updatedUser = UserRepository.save(users);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long username)
         throws ResourceNotFoundException {
        User users = UserRepository.findById(username)
       .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + username));

        UserRepository.delete(users);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
    
	@PostMapping("/forgot-password")
	public Email forgotPassword(@RequestParam String email) throws AddressException, MessagingException, IOException  {

		String response = userService.forgotPassword(email);

		if (!response.startsWith("Invalid")) {
			
			response = "http://localhost:8080/reset-password?token=" + response;
			
			emails.setEmail("mohamedyaakoubiweb@gmail.com");
			emails.setSubject("reset link");
			emails.setComment(response);
			emailService.sendmail(emails);
			
		}
		
		return emails;
	}

	@PutMapping("/reset-password")
	public String resetPassword(@RequestParam String token,
			@RequestParam String password) {

		return userService.resetPassword(token, password);
	}
}

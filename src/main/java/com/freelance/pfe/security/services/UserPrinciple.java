package com.freelance.pfe.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freelance.pfe.model.User;

public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

    private  Long id;

    private String nom;
    
     private String prenom;

    private String username;

    private String email;
    
    private String cv;
    
    private String adresse;
    
    private String skills;
    
    private String aboutme;
    
    private String date_nais;
    
    private int telephone;
    
    private String photo;
    
    private int cin;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(Long id, String nom, String prenom, String username, String email, String password,
    Collection<? extends GrantedAuthority> authorities, String cv, String adresse, String skills, String photo, String aboutme,
     String date_nais, int telephone, int cin)
    {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.cv = cv;
        this.adresse = adresse;
        this.skills = skills;
        this.photo = photo;
        this.aboutme = aboutme;
        this.date_nais = date_nais;
        this.telephone = telephone;
        this.cin = cin;
        
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.getCv(),
                user.getAdresse(),
                user.getSkills(),
                user.getPhoto(),
                user.getDate_nais(),
                user.getAboutme(),
                user.getTelephone(),
                user.getCin()
        );
                
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCv() {
        return cv;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getSkills() {
        return skills;
    }

    public String getAboutme() {
        return aboutme;
    }

    public String getDate_nais() {
        return date_nais;
    }

    public int getTelephone() {
        return telephone;
    }
    
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }
    
    
    
}
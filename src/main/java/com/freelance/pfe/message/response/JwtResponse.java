package com.freelance.pfe.message.response;

import java.util.Collection;
import java.util.List;


public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String username;
        private long id;
        private String email;
        private String nom;
        private String prenom;
        private String skills;
        private String cv;
        private String adresse;
        private int telephone;
        private String date_nais;
        private String aboutme;
        private String photo;
        private int cin;
        
	private List<String> roles;
   
    

	public JwtResponse(String accessToken, long id, String username, String nom, String prenom,
                String email, List<String> roles, int telephone, String skills, String cv, String adresse, String photo,
                String date_nais, String aboutme, int cin) {
		this.token = accessToken;
                this.id = id;
		this.username = username;
                this.nom = nom;
                this.prenom = prenom;
                this.email = email;
                this.skills = skills;
                this.cv = cv;
                this.adresse = adresse;
                this.photo = photo;
                this.telephone = telephone;
                this.date_nais = date_nais;
                this.aboutme = aboutme;
		this.roles = roles;
                this.cin=cin;
	}

    

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getDate_nais() {
        return date_nais;
    }

    public void setDate_nais(String date_nais) {
        this.date_nais = date_nais;
    }

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
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
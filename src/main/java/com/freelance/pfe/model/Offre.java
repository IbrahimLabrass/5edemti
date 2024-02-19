/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freelance.pfe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author ya39o
 */

@Entity

public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
                        
    private String titre;
        
        
    
    private String date_publication;
         
    
    private String dead_line;
    
    
    
    private Integer min;
    
    
    private Integer max;
    
    
    private String contenu;
    
    
    private String skills;
    
    private String document;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Offre(Integer min, Integer max, String titre, String document, String skills, String date_publication, String dead_line, String contenu, User user, boolean b) {
        this.id = id;
        this.titre = titre;
        this.skills = skills;
        this.min = min;
        this.max = max;
        this.date_publication = date_publication;
        this.dead_line = dead_line;
        this.contenu = contenu;
        this.document = document;
        this.user = user;
    }
    public Offre() {
		
	}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", min='" + min + '\'' +
                ", max='" + max + '\'' +
                ", contenu='" + contenu + '\'' +
                ", titre='" + titre + '\'' +
                ", skills='" + skills + '\'' +
                ", dead_line='" + dead_line + '\'' +
                ", date_publication='" + date_publication + '\'' +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public String getDate_publication() {
		return date_publication;
	}
	public void setDate_publication(String date_publication) {
		this.date_publication = date_publication;
	}
	public String getDead_line() {
		return dead_line;
	}
	public void setDead_line(String dead_line) {
		this.dead_line = dead_line;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
    
	
	
}

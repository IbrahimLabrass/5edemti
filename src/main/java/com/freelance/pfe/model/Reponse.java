/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freelance.pfe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author HP
 */
@Entity
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_recepteur", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User id_recepteur;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_emmeteur", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User id_emmeteur;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_message", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Message id_message;
    
    
    private String date;
    
    
    private String texte;

    public Long getId() {
        return id;
    }

    

    public User getId_recepteur() {
        return id_recepteur;
    }

    public void setId_recepteur(User id_recepteur) {
        this.id_recepteur = id_recepteur;
    }

    public User getId_emmeteur() {
        return id_emmeteur;
    }

    public void setId_emmeteur(User id_emmeteur) {
        this.id_emmeteur = id_emmeteur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Message getId_message() {
        return id_message;
    }

    public void setId_message(Message id_message) {
        this.id_message = id_message;
    }
    
    

}

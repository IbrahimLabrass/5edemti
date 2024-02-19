package com.freelance.pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelance.pfe.model.Condidature;
import com.freelance.pfe.model.Demande;

public interface DemandeRepository extends JpaRepository<Demande,Long>  {

}
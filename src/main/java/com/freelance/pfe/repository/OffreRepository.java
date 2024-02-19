/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freelance.pfe.repository;

import com.freelance.pfe.model.Offre;
import com.freelance.pfe.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface OffreRepository extends JpaRepository<Offre,Long> {
    Optional<Offre> findByTitre(String titre);
    List<Offre> findByUser(String user);
    
    
}
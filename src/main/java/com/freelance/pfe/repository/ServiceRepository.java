/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freelance.pfe.repository;
import com.freelance.pfe.model.Service;
import com.freelance.pfe.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author ya39o
 */
public interface ServiceRepository extends JpaRepository<Service,Long> {
    Optional<Service> findByTitre(String titre);

}

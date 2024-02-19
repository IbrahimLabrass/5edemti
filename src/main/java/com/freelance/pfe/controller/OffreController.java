/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freelance.pfe.controller;

import com.freelance.pfe.exception.ResourceNotFoundException;
import com.freelance.pfe.model.Offre;
import com.freelance.pfe.model.User;
import com.freelance.pfe.repository.OffreRepository;
import com.freelance.pfe.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ya39o
 */

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class OffreController {
     @Autowired
    private OffreRepository OffreRepository;
    private Optional<User> user;
    
    @RequestMapping("/hello")
	public String index() {
		return "Spring Boot Example!!";
	}

    @GetMapping("/offres")
    public List<Offre> getAllOffres() {
        return  (List<Offre>) OffreRepository.findAll();
        //return (List<Offre>) OffreRepository.findAll();
    }


    @GetMapping("/offres/{id}")
    public ResponseEntity<Offre> getOffreById(@PathVariable(value = "id") Long titre)
        throws ResourceNotFoundException {
        Offre offres = OffreRepository.findById(titre)
          .orElseThrow(() -> new ResourceNotFoundException("Offre not found for this id :: " + titre));
        return ResponseEntity.ok().body(offres);
    }
    
    @GetMapping("/offre/{id}")
    public List<Offre> getOffreByUser(@PathVariable(value = "id") String titre)
    {
         //Optional<User> users = UserRepository.findById(titre);
        return  (List<Offre>) OffreRepository.findByUser(titre);
    }
    
    @PostMapping("/offre")
    public Offre createOffre(@Valid @RequestBody Offre offres) {
        return OffreRepository.save(offres);
    }

    @PutMapping("/offres/{id}")
    public ResponseEntity<Offre> updateOffre(@PathVariable(value = "id") Long titre,
         @Valid @RequestBody Offre offresDetails) throws ResourceNotFoundException {
        Offre offres = OffreRepository.findById(titre)
        .orElseThrow(() -> new ResourceNotFoundException("Offre not found for this id :: " + titre));

        offres.setTitre(offresDetails.getTitre());
        offres.setSkills(offresDetails.getSkills());
        offres.setDate_publication(offresDetails.getDate_publication());
        offres.setDead_line(offresDetails.getDead_line());
        offres.setMin(offresDetails.getMin());
        offres.setMax(offresDetails.getMax());
        offres.setContenu(offresDetails.getContenu());
        offres.setDocument(offresDetails.getDocument());
        offres.setUser(offresDetails.getUser());
        final Offre updatedOffre = OffreRepository.save(offres);
        return ResponseEntity.ok(updatedOffre);
    }

    @DeleteMapping("/offres/{id}")
    public Map<String, Boolean> deleteOffre(@PathVariable(value = "id") Long titre)
         throws ResourceNotFoundException {
        Offre offres = OffreRepository.findById(titre)
       .orElseThrow(() -> new ResourceNotFoundException("Offre not found for this id :: " + titre));

        OffreRepository.delete(offres);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

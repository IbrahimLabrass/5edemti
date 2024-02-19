package com.freelance.pfe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.pfe.exception.ResourceNotFoundException;
import com.freelance.pfe.model.Demande;
import com.freelance.pfe.model.User;
import com.freelance.pfe.repository.DemandeRepository;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class DemandeController {

	@Autowired
	   private DemandeRepository DemandeRepository;
	   private Optional<User> user;
	   
	   

	   @GetMapping("/demandes")
	   public List<Demande> getAllDemandes() {
	       return  (List<Demande>) DemandeRepository.findAll();
	       //return (List<Demande>) DemandeRepository.findAll();
	   }


	   @GetMapping("/demande/{id}")
	   public ResponseEntity<Demande> getDemandeById(@PathVariable(value = "id") Long titre)
	       throws ResourceNotFoundException {
	       Demande demandes = DemandeRepository.findById(titre)
	         .orElseThrow(() -> new ResourceNotFoundException("Demande not found for this id :: " + titre));
	       return ResponseEntity.ok().body(demandes);
	   }
	     
	   
	   @PostMapping("/demande")
	   public Demande createDemande(@Valid @RequestBody Demande demandes) {
	       return DemandeRepository.save(demandes);
	   }

	   @PutMapping("/demandes/{id}")
	   public ResponseEntity<Demande> updateDemande(@PathVariable(value = "id") Long titre,
	        @Valid @RequestBody Demande demandesDetails) throws ResourceNotFoundException {
	       Demande demandes = DemandeRepository.findById(titre)
	       .orElseThrow(() -> new ResourceNotFoundException("Demande not found for this id :: " + titre));
	       
	       demandes.setDate(demandesDetails.getDate());
	       demandes.setDescription(demandesDetails.getDescription());
	       final Demande updatedDemande = DemandeRepository.save(demandes);
	       return ResponseEntity.ok(updatedDemande);
	   }

	   @DeleteMapping("/demandes/{id}")
	   public Map<String, Boolean> deleteDemande(@PathVariable(value = "id") Long titre)
	        throws ResourceNotFoundException {
	       Demande demandes = DemandeRepository.findById(titre)
	      .orElseThrow(() -> new ResourceNotFoundException("Demande not found for this id :: " + titre));

	       DemandeRepository.delete(demandes);
	       Map<String, Boolean> response = new HashMap<>();
	       response.put("deleted", Boolean.TRUE);
	       return response;
	   }

}

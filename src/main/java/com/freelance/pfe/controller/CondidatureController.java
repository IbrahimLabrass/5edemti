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
import com.freelance.pfe.model.Condidature;
import com.freelance.pfe.model.User;
import com.freelance.pfe.repository.CondidatureRepository;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class CondidatureController {

    @Autowired
   private CondidatureRepository CondidatureRepository;
   private Optional<User> user;
   
   

   @GetMapping("/condidatures")
   public List<Condidature> getAllCondidatures() {
       return  (List<Condidature>) CondidatureRepository.findAll();
       //return (List<Condidature>) CondidatureRepository.findAll();
   }


   @GetMapping("/condidature/{id}")
   public ResponseEntity<Condidature> getCondidatureById(@PathVariable(value = "id") Long titre)
       throws ResourceNotFoundException {
       Condidature condidatures = CondidatureRepository.findById(titre)
         .orElseThrow(() -> new ResourceNotFoundException("Condidature not found for this id :: " + titre));
       return ResponseEntity.ok().body(condidatures);
   }
     
   
   @PostMapping("/condidature")
   public Condidature createCondidature(@Valid @RequestBody Condidature condidatures) {
       return CondidatureRepository.save(condidatures);
   }

   @PutMapping("/condidatures/{id}")
   public ResponseEntity<Condidature> updateCondidature(@PathVariable(value = "id") Long id,
        @Valid @RequestBody Condidature condidaturesDetails) throws ResourceNotFoundException {
       Condidature condidatures = CondidatureRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Condidature not found for this id :: " + id));
       
       condidatures.setPrix(condidaturesDetails.getPrix());
       condidatures.setDescription(condidaturesDetails.getDescription());
       condidatures.setTemps(condidaturesDetails.getTemps());
       condidatures.setEtat(condidaturesDetails.getEtat());
       final Condidature updatedCondidature = CondidatureRepository.save(condidatures);
       return ResponseEntity.ok(updatedCondidature);
   }

   @DeleteMapping("/condidatures/{id}")
   public Map<String, Boolean> deleteCondidature(@PathVariable(value = "id") Long titre)
        throws ResourceNotFoundException {
       Condidature condidatures = CondidatureRepository.findById(titre)
      .orElseThrow(() -> new ResourceNotFoundException("Condidature not found for this id :: " + titre));

       CondidatureRepository.delete(condidatures);
       Map<String, Boolean> response = new HashMap<>();
       response.put("deleted", Boolean.TRUE);
       return response;
   }

}

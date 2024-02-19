/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freelance.pfe.controller;

import com.freelance.pfe.exception.ResourceNotFoundException;
import com.freelance.pfe.model.Service;
import com.freelance.pfe.repository.ServiceRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

/**
 *
 * @author ya39o
 */
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ServiceController {
     @Autowired
    private ServiceRepository ServiceRepository;
    
   

    @GetMapping("/services")
    public List<Service> getAllServices() {
        return  (List<Service>) ServiceRepository.findAll();
        //return (List<Service>) ServiceRepository.findAll();
    }


    @GetMapping("/services/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable(value = "id") Long titre)
        throws ResourceNotFoundException {
        Service services = ServiceRepository.findById(titre)
          .orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: " + titre));
        return ResponseEntity.ok().body(services);
    }
    
    @PostMapping("/service")
    public Service createService(@Valid @RequestBody Service services) {
        return ServiceRepository.save(services);
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<Service> updateService(@PathVariable(value = "id") Long titre,
         @Valid @RequestBody Service servicesDetails) throws ResourceNotFoundException {
        Service services = ServiceRepository.findById(titre)
        .orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: " + titre));

        services.setTitre(servicesDetails.getTitre());
        services.setPrix(servicesDetails.getPrix());
        services.setDescription(servicesDetails.getDescription());
        services.setDocument(servicesDetails.getDocument());
        services.setUser(servicesDetails.getUser());
        final Service updatedService = ServiceRepository.save(services);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/services/{id}")
    public Map<String, Boolean> deleteService(@PathVariable(value = "id") Long titre)
         throws ResourceNotFoundException {
        Service services = ServiceRepository.findById(titre)
       .orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: " + titre));

        ServiceRepository.delete(services);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

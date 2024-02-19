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
import com.freelance.pfe.model.Notification;
import com.freelance.pfe.model.User;
import com.freelance.pfe.repository.NotificationRepository;


@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class NotificationController {

	@Autowired
	   private NotificationRepository NotificationRepository;
	   private Optional<User> user;
	   
	   

	   @GetMapping("/notifications")
	   public List<Notification> getAllNotifications() {
	       return  (List<Notification>) NotificationRepository.findAll();
	       //return (List<Notification>) NotificationRepository.findAll();
	   }


	   @GetMapping("/notification/{id}")
	   public ResponseEntity<Notification> getNotificationById(@PathVariable(value = "id") Long titre)
	       throws ResourceNotFoundException {
	       Notification notifications = NotificationRepository.findById(titre)
	         .orElseThrow(() -> new ResourceNotFoundException("Notification not found for this id :: " + titre));
	       return ResponseEntity.ok().body(notifications);
	   }
	     
	   
	   @PostMapping("/notification")
	   public Notification createNotification(@Valid @RequestBody Notification notifications) {
	       return NotificationRepository.save(notifications);
	   }

	   @PutMapping("/notifications/{id}")
	   public ResponseEntity<Notification> updateNotification(@PathVariable(value = "id") Long titre,
	        @Valid @RequestBody Notification notificationsDetails) throws ResourceNotFoundException {
	       Notification notifications = NotificationRepository.findById(titre)
	       .orElseThrow(() -> new ResourceNotFoundException("Notification not found for this id :: " + titre));
	       
	       notifications.setDate(notificationsDetails.getDate());
	       notifications.setTexte(notificationsDetails.getTexte());
	       notifications.setEtat(notificationsDetails.getEtat());
	       final Notification updatedNotification = NotificationRepository.save(notifications);
	       return ResponseEntity.ok(updatedNotification);
	   }

	   @DeleteMapping("/notifications/{id}")
	   public Map<String, Boolean> deleteNotification(@PathVariable(value = "id") Long titre)
	        throws ResourceNotFoundException {
	       Notification notifications = NotificationRepository.findById(titre)
	      .orElseThrow(() -> new ResourceNotFoundException("Notification not found for this id :: " + titre));

	       NotificationRepository.delete(notifications);
	       Map<String, Boolean> response = new HashMap<>();
	       response.put("deleted", Boolean.TRUE);
	       return response;
	   }
}

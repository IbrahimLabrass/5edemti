package com.freelance.pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelance.pfe.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long>  {

}
package com.studmed.notification.infraestructure.persistance.jpa.repositories;

import com.studmed.notification.domain.model.aggregates.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByTitle(String title);
    Boolean existsByTitle(String title);
    Boolean existsByTitleAndIdIsNot(String title, Long id);

}
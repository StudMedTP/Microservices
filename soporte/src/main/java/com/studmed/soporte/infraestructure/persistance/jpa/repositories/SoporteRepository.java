package com.studmed.soporte.infraestructure.persistance.jpa.repositories;

import com.studmed.soporte.domain.model.aggregates.Soporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SoporteRepository extends JpaRepository<Soporte, Long> {
    Optional<Soporte> findByTicketSentTitle(String ticketSentTitle);
    Boolean existsByTicketSentTitle(String ticketSentTitle);
    Boolean existsByTicketSentTitleAndIdIsNot(String ticketSentTitle, Long id);
}
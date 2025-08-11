package com.studmed.soporte.ticket.infraestructure.persistance.jpa.repositories;

import com.studmed.soporte.ticket.domain.model.aggregates.Soporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoporteRepository extends JpaRepository<Soporte, Long> {
    Boolean existsByTitle(String title);
    Boolean existsByTitleAndIdIsNot(String title, Long id);
}
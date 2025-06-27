package com.studmed.soporte.infraestructure.persistance.jpa.repositories;

import com.studmed.soporte.domain.model.aggregates.Soporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SoporteRepository extends JpaRepository<Soporte, Long> {
    Optional<Soporte> findByDate(String date);
    Boolean existsByDate(String date);
    Boolean existsByDateAndIdIsNot(String date, Long id);
}
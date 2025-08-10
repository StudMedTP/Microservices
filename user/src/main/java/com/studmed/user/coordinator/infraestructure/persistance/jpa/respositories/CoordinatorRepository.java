package com.studmed.user.coordinator.infraestructure.persistance.jpa.respositories;

import com.studmed.user.coordinator.domain.model.aggregates.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
    Boolean existsByCoordinatorCode(String coordinatorCode);
}
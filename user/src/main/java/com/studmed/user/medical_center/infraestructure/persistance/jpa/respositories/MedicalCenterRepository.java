package com.studmed.user.medical_center.infraestructure.persistance.jpa.respositories;

import com.studmed.user.medical_center.domain.model.aggregates.MedicalCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalCenterRepository extends JpaRepository<MedicalCenter, Long> {
    Boolean existsByName(String name);
}
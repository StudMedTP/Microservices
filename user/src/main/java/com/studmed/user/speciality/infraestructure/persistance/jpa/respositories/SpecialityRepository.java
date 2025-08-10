package com.studmed.user.speciality.infraestructure.persistance.jpa.respositories;

import com.studmed.user.speciality.domain.model.aggregates.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    Boolean existsByName(String name);
}
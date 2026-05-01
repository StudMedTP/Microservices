package com.studmed.evaluation.classroom.infraestructure.persistance.jpa.repositories;

import com.studmed.evaluation.classroom.domain.model.aggregate.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findAllByTeacherId(Long studentId);

    Boolean existsByNameAndMedicalCenterId(String name, Long medicalCenterId);
}
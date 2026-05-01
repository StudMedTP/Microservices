package com.studmed.evaluation.grade.infraestructure.persistance.jpa.repositories;

import com.studmed.evaluation.grade.domain.model.aggregate.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllByClassroomStudent_Id(Long classroomStudentId);
}
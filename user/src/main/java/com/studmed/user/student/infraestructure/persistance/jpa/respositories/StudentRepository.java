package com.studmed.user.student.infraestructure.persistance.jpa.respositories;

import com.studmed.user.student.domain.model.aggregates.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Boolean existsByStudentCode(String studentCode);
}
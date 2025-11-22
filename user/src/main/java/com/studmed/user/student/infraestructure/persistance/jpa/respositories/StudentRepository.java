package com.studmed.user.student.infraestructure.persistance.jpa.respositories;

import com.studmed.user.student.domain.model.aggregates.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Boolean existsByStudentCode(String studentCode);
    Optional<Student> findByUser_Id(Long userId);
    List<Student> findByTeacher_User_Id(Long teacherId);
}
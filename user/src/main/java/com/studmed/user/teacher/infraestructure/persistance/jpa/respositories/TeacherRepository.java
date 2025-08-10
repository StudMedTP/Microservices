package com.studmed.user.teacher.infraestructure.persistance.jpa.respositories;

import com.studmed.user.teacher.domain.model.aggregates.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Boolean existsByTeacherCode(String teacherCode);
}
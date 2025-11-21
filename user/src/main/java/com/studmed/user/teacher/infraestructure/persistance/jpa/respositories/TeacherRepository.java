package com.studmed.user.teacher.infraestructure.persistance.jpa.respositories;

import com.studmed.user.teacher.domain.model.aggregates.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Boolean existsByTeacherCode(String teacherCode);
    Optional<Teacher> findByDailyCodeAndId(String dailyCode, Long id);
}
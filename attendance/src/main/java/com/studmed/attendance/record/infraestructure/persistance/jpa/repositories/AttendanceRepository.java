package com.studmed.attendance.record.infraestructure.persistance.jpa.repositories;

import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAllByStudentId(Long studentId);
}
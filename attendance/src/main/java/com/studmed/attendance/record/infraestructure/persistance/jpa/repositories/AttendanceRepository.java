package com.studmed.attendance.record.infraestructure.persistance.jpa.repositories;

import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAllByStatusAndStudentId(String status, Long studentId);
    Optional<Attendance> findTopByStudentIdOrderByCreatedAtDesc(Long studentId);
}
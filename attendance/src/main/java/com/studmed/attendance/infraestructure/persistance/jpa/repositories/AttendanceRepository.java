package com.studmed.attendance.infraestructure.persistance.jpa.repositories;

import com.studmed.attendance.domain.model.aggregates.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByVerificationToken(String verificationToken);
    Boolean existsByVerificationToken(String verificationToken);
    Boolean existsByVerificationTokenAndIdIsNot(String verificationToken, Long id);
}
package com.studmed.evaluation.clinichistory.infraestructure.persistance.jpa.repositories;

import com.studmed.evaluation.clinichistory.domain.model.aggregate.ClinicHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicHistoryRepository extends JpaRepository<ClinicHistory, Long> {
    List<ClinicHistory> findAllByStudentId(Long studentId);
}

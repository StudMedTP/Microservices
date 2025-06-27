package com.studmed.evaluation.infraestructure.persistance.jpa.repositories;

import com.studmed.evaluation.domain.model.aggregates.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    Optional<Evaluation> findByTitle(String title);
    Boolean existsByTitle(String title);
    Boolean existsByTitleAndIdIsNot(String title, Long id);
}
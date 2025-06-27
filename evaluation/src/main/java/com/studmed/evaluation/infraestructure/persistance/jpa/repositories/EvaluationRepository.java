package com.studmed.evaluation.infraestructure.persistance.jpa.repositories;

import com.studmed.evaluation.domain.model.aggregates.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    Optional<Evaluation> findByName(String name);
    Boolean existsByName(String name);
    Boolean existsByNameAndIdIsNot(String name, Long id);
}
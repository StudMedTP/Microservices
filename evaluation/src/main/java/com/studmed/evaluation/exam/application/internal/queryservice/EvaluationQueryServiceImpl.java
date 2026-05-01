package com.studmed.evaluation.exam.application.internal.queryservice;

import com.studmed.evaluation.exam.domain.model.aggregates.Evaluation;
import com.studmed.evaluation.exam.domain.model.queries.GetAllEvaluationQuery;
import com.studmed.evaluation.exam.domain.model.queries.GetEvaluationByIdQuery;
import com.studmed.evaluation.exam.domain.service.EvaluationQueryService;
import com.studmed.evaluation.exam.infraestructure.persistance.jpa.repositories.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationQueryServiceImpl implements EvaluationQueryService {

    private final EvaluationRepository evaluationRepository;

    public EvaluationQueryServiceImpl(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public Optional<Evaluation> handle(GetEvaluationByIdQuery query) {
        return evaluationRepository.findById(query.id());
    }

    @Override
    public List<Evaluation> handle(GetAllEvaluationQuery query) {
        return evaluationRepository.findAll();
    }
}
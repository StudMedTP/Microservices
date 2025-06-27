package com.studmed.evaluation.domain.service;

import com.studmed.evaluation.domain.model.aggregates.Evaluation;
import com.studmed.evaluation.domain.model.queries.GetAllEvaluationQuery;
import com.studmed.evaluation.domain.model.queries.GetEvaluationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface EvaluationQueryService {
    List<Evaluation> handle(GetAllEvaluationQuery query);
    Optional<Evaluation> handle(GetEvaluationByIdQuery query);
}
package com.studmed.evaluation.exam.domain.service;

import com.studmed.evaluation.exam.domain.model.aggregates.Evaluation;
import com.studmed.evaluation.exam.domain.model.queries.GetAllEvaluationQuery;
import com.studmed.evaluation.exam.domain.model.queries.GetEvaluationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface EvaluationQueryService {
    List<Evaluation> handle(GetAllEvaluationQuery query);
    Optional<Evaluation> handle(GetEvaluationByIdQuery query);
}
package com.studmed.evaluation.exam.domain.service;

import com.studmed.evaluation.exam.domain.model.aggregates.Evaluation;
import com.studmed.evaluation.exam.domain.model.commands.CreateEvaluationCommand;
import com.studmed.evaluation.exam.domain.model.commands.DeleteEvaluationCommand;
import com.studmed.evaluation.exam.domain.model.commands.UpdateEvaluationCommand;

import java.util.Optional;

public interface EvaluationCommandService {
    Long handle (CreateEvaluationCommand command);
    Optional<Evaluation> handle (UpdateEvaluationCommand command);
    void handle (DeleteEvaluationCommand command);
}
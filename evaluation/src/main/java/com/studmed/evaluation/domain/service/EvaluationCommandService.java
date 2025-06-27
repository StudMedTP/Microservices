package com.studmed.evaluation.domain.service;

import com.studmed.evaluation.domain.model.aggregates.Evaluation;
import com.studmed.evaluation.domain.model.commands.CreateEvaluationCommand;
import com.studmed.evaluation.domain.model.commands.DeleteEvaluationCommand;
import com.studmed.evaluation.domain.model.commands.UpdateEvaluationCommand;

import java.util.Optional;

public interface EvaluationCommandService {
    Long handle (CreateEvaluationCommand command);
    Optional<Evaluation> handle (UpdateEvaluationCommand command);
    void handle (DeleteEvaluationCommand command);
}
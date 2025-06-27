package com.studmed.evaluation.application.internal.commandservice;

import com.studmed.evaluation.domain.model.aggregates.Evaluation;
import com.studmed.evaluation.domain.model.commands.CreateEvaluationCommand;
import com.studmed.evaluation.domain.model.commands.DeleteEvaluationCommand;
import com.studmed.evaluation.domain.model.commands.UpdateEvaluationCommand;
import com.studmed.evaluation.domain.service.EvaluationCommandService;
import com.studmed.evaluation.infraestructure.persistance.jpa.repositories.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvaluationCommandServiceImpl implements EvaluationCommandService {

    private final EvaluationRepository evaluationRepository;
    public EvaluationCommandServiceImpl(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public Long handle(CreateEvaluationCommand command) {
        if (evaluationRepository.existsByName(command.name())){
            throw new IllegalArgumentException("Evaluation Already Exists");
        }
        Evaluation evaluation = new Evaluation(command);
        try {
            evaluationRepository.save(evaluation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving evaluation" + e.getMessage());
        }
        return evaluation.getId();
    }

    @Override
    public Optional<Evaluation> handle (UpdateEvaluationCommand command) {

        if (evaluationRepository.existsByNameAndIdIsNot(command.name(), command.id())){
            throw new IllegalArgumentException("Evaluation with same name already exists");
        }

        var result = evaluationRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Evaluation does not exist");
        }

        var evaluationToUpdate = result.get();
        try {
            var updatedEvaluation = evaluationRepository.save(evaluationToUpdate.updateEvaluation(
                    command.name(),
                    command.description(),
                    command.price(),
                    command.imageUrl(),
                    command.rating(),
                    command.category()));
            return Optional.of(updatedEvaluation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user" + e.getMessage());
        }
    }

    @Override
    public void handle (DeleteEvaluationCommand command) {

        if (!evaluationRepository.existsById(command.id())){
            throw new IllegalArgumentException("Evaluation does not exist");
        }
        try {
            evaluationRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting evaluation" + e.getMessage());
        }
    }
}
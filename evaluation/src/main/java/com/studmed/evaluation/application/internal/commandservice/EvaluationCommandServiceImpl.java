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
        if (evaluationRepository.existsByTitle(command.title())){
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

        if (evaluationRepository.existsByTitleAndIdIsNot(command.title(), command.id())){
            throw new IllegalArgumentException("Evaluation with same title already exists");
        }

        var result = evaluationRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Evaluation does not exist");
        }

        var evaluationToUpdate = result.get();
        try {
            var updatedEvaluation = evaluationRepository.save(evaluationToUpdate.updateEvaluation(
                    command.title(),
                    command.hospitalName(),
                    command.courseName(),
                    command.description(),
                    command.startDate(),
                    command.evaluationState(),
                    command.feedback(),
                    command.teacherName(),
                    command.evaluationGrade()));
            return Optional.of(updatedEvaluation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving evaluation" + e.getMessage());
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
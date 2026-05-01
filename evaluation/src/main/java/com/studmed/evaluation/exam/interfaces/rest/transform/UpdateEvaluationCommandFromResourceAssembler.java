package com.studmed.evaluation.exam.interfaces.rest.transform;

import com.studmed.evaluation.exam.domain.model.commands.UpdateEvaluationCommand;
import com.studmed.evaluation.exam.interfaces.rest.resource.UpdateEvaluationResource;

public class UpdateEvaluationCommandFromResourceAssembler {
    public static UpdateEvaluationCommand toCommandFromResource(Long id, UpdateEvaluationResource resource) {
        return new UpdateEvaluationCommand(
                id,
                resource.title(),
                resource.hospitalName(),
                resource.courseName(),
                resource.description(),
                resource.startDate(),
                resource.evaluationState(),
                resource.feedback(),
                resource.teacherName(),
                resource.evaluationGrade());
    }
}
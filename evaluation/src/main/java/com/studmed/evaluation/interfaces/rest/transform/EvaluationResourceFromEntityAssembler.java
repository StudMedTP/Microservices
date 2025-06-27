package com.studmed.evaluation.interfaces.rest.transform;

import com.studmed.evaluation.domain.model.aggregates.Evaluation;
import com.studmed.evaluation.interfaces.rest.resource.EvaluationResource;

public class EvaluationResourceFromEntityAssembler {
    public static EvaluationResource toResourceFromEntity(Evaluation entity ) {
        return new EvaluationResource(
                entity.getId(),
                entity.getTitle(),
                entity.getHospitalName(),
                entity.getCourseName(),
                entity.getDescription(),
                entity.getStartDate(),
                entity.getEvaluationState(),
                entity.getFeedback(),
                entity.getTeacherName(),
                entity.getEvaluationGrade());
    }
}
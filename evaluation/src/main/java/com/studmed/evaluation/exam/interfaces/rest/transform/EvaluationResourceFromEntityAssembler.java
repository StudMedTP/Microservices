package com.studmed.evaluation.exam.interfaces.rest.transform;

import com.studmed.evaluation.exam.domain.model.aggregates.Evaluation;
import com.studmed.evaluation.exam.interfaces.rest.resource.EvaluationResource;

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
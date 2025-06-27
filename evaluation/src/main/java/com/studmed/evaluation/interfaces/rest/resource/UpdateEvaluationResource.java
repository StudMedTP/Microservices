package com.studmed.evaluation.interfaces.rest.resource;

public record UpdateEvaluationResource(String title,
                                       String hospitalName,
                                       String courseName,
                                       String description,
                                       String startDate,
                                       String evaluationState,
                                       String feedback,
                                       String teacherName,
                                       String evaluationGrade) {
}
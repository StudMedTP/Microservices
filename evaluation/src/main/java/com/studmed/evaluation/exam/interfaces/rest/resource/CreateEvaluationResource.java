package com.studmed.evaluation.exam.interfaces.rest.resource;

public record CreateEvaluationResource(String title,
                                       String hospitalName,
                                       String courseName,
                                       String description,
                                       String startDate,
                                       String evaluationState,
                                       String feedback,
                                       String teacherName,
                                       String evaluationGrade) {
}
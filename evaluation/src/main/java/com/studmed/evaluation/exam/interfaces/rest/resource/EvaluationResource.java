package com.studmed.evaluation.exam.interfaces.rest.resource;

public record EvaluationResource(Long id,
                                 String title,
                                 String hospitalName,
                                 String courseName,
                                 String description,
                                 String startDate,
                                 String evaluationState,
                                 String feedback,
                                 String teacherName,
                                 String evaluationGrade) {
}
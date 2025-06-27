package com.studmed.evaluation.domain.model.commands;

public record CreateEvaluationCommand(String title,
                                      String hospitalName,
                                      String courseName,
                                      String description,
                                      String startDate,
                                      String evaluationState,
                                      String feedback,
                                      String teacherName,
                                      String evaluationGrade) {
}
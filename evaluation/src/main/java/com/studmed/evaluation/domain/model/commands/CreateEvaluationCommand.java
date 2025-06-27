package com.studmed.evaluation.domain.model.commands;

public record CreateEvaluationCommand(String name,
                                      String description,
                                      Double price,
                                      String imageUrl,
                                      Double rating,
                                      String category) {
}
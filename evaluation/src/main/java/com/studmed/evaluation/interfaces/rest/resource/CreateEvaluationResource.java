package com.studmed.evaluation.interfaces.rest.resource;

public record CreateEvaluationResource(String name,
                                       String description,
                                       Double price,
                                       String imageUrl,
                                       Double rating,
                                       String category) {
}
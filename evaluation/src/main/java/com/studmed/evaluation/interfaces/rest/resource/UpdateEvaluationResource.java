package com.studmed.evaluation.interfaces.rest.resource;

public record UpdateEvaluationResource(String name,
                                       String description,
                                       Double price,
                                       String imageUrl,
                                       Double rating,
                                       String category) {
}
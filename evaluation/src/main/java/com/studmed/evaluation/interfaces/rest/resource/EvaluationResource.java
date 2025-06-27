package com.studmed.evaluation.interfaces.rest.resource;

public record EvaluationResource(Long id,
                                 String name,
                                 String description,
                                 Double price,
                                 String imageUrl,
                                 Double rating,
                                 String category) {
}
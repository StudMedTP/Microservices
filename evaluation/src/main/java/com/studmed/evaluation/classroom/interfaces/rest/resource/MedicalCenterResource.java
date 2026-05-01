package com.studmed.evaluation.classroom.interfaces.rest.resource;

public record MedicalCenterResource(
        Long id,
        String name,
        Double latitude,
        Double longitude
) {}

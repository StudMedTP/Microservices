package com.studmed.user.medical_center.interfaces.rest.resource;

import jakarta.validation.constraints.NotBlank;

public record CreateMedicalCenterResource(
        @NotBlank()
        String name) {}
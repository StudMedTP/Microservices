package com.studmed.user.coordinator.interfaces.rest.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCoordinatorResource(
        @NotBlank()
        String coordinatorCode,
        @NotNull()
        @Min(value = 1)
        Long userId) {}
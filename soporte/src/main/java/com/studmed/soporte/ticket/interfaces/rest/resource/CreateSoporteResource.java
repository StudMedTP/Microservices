package com.studmed.soporte.ticket.interfaces.rest.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSoporteResource(
        @NotBlank()
        String title,
        @NotBlank()
        String message,
        @NotNull()
        @Min(value = 1)
        Long studentId,
        @NotNull()
        @Min(value = 1)
        Long teacherId) {}
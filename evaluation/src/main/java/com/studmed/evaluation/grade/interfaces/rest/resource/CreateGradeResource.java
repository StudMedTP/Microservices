package com.studmed.evaluation.grade.interfaces.rest.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateGradeResource(
        @NotNull()
        @Min(value = 1)
        Long classroomStudentId,
        @NotNull()
        @Min(value = 1)
        Long value,
        @NotBlank()
        String description) {}
package com.studmed.evaluation.classroom.interfaces.rest.resource;

import jakarta.validation.constraints.*;

public record CreateClassroomResource(
        @NotBlank()
        String name,
        @NotNull()
        @Min(value = 1)
        Long medicalCenterId,
        @NotNull()
        @Min(value = 1)
        Long teacherId) {}
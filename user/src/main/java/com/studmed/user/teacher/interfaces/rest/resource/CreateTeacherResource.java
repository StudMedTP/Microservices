package com.studmed.user.teacher.interfaces.rest.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTeacherResource(
        @NotBlank()
        String teacherCode,
        @NotNull()
        @Min(value = 1)
        Long userId,
        @NotNull()
        @Min(value = 1)
        Long medicalCenterId,
        @NotNull()
        @Min(value = 1)
        Long specialityId,
        @NotNull()
        @Min(value = 1)
        Long coordinatorId) {}
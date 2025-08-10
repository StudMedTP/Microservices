package com.studmed.user.student.intefaces.rest.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateStudentResource(
        @NotBlank()
        String studentCode,
        @NotNull()
        @Min(value = 1)
        Long userId,
        @NotNull()
        @Min(value = 1)
        Long teacherId) {}
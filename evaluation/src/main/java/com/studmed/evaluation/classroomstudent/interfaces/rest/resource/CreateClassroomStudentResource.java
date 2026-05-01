package com.studmed.evaluation.classroomstudent.interfaces.rest.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateClassroomStudentResource(
        @NotNull()
        @Min(value = 1)
        Long classroomId,
        @NotNull()
        @Min(value = 1)
        Long studentId) {}
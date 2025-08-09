package com.studmed.user.student.intefaces.rest.resource;

import jakarta.validation.constraints.NotBlank;

public record CreateStudentResource(
        @NotBlank()
        String studentCode) {
}
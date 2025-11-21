package com.studmed.attendance.record.interfaces.rest.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAttendanceResource(
        @NotNull()
        @Min(value = 1)
        Long studentId,
        @NotNull()
        @Min(value = 1)
        Long medicalCenterId,
        @NotBlank()
        String status) {}
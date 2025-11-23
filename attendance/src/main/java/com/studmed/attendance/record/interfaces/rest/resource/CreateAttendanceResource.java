package com.studmed.attendance.record.interfaces.rest.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateAttendanceResource(
        @NotNull()
        @Min(value = 1)
        Long studentId,
        @NotNull()
        @Min(value = 1)
        Long medicalCenterId,
        @NotNull
        LocalDateTime date) {}
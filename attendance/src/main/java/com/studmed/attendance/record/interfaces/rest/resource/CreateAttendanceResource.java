package com.studmed.attendance.record.interfaces.rest.resource;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateAttendanceResource(
        @NotNull()
        @Min(value = 1)
        Long studentId,
        @NotNull()
        @Min(value = 1)
        Long teacherId,
        @NotNull
        @DecimalMin(value = "-90.0")
        @DecimalMax(value = "90.0")
        Double latitude,
        @NotNull
        @DecimalMin(value = "-180.0")
        @DecimalMax(value = "180.0")
        Double longitude) {}
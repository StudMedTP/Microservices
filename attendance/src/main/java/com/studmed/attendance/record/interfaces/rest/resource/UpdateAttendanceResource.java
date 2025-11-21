package com.studmed.attendance.record.interfaces.rest.resource;

import jakarta.validation.constraints.NotBlank;

public record UpdateAttendanceResource(
        @NotBlank()
        String status) {}
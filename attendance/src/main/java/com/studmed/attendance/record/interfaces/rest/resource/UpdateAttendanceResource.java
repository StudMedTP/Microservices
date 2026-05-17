package com.studmed.attendance.record.interfaces.rest.resource;

import jakarta.validation.constraints.NotNull;

public record UpdateAttendanceResource(
        @NotNull
        Boolean isPartial) {}
package com.studmed.attendance.record.interfaces.rest.resource;

public record StudentResource(
        Long id,
        String studentCode,
        Long teacherId
) {}

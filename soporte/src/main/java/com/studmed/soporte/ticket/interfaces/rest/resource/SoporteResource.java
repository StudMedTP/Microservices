package com.studmed.soporte.ticket.interfaces.rest.resource;

import com.studmed.soporte.ticket.domain.model.client.StudentResource;
import com.studmed.soporte.ticket.domain.model.client.TeacherResource;

import java.util.Date;

public record SoporteResource(
        Long id,
        String title,
        String message,
        StudentResource student,
        TeacherResource teacher,
        Date createdAt) {}
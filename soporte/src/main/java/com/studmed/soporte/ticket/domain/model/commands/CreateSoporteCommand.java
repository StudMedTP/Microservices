package com.studmed.soporte.ticket.domain.model.commands;

public record CreateSoporteCommand(String title,
                                   String message,
                                   Long studentId,
                                   Long teacherId) {}
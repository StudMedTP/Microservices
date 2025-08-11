package com.studmed.soporte.ticket.domain.model.commands;

public record UpdateSoporteCommand(Long id,
                                   String title,
                                   String message) {}
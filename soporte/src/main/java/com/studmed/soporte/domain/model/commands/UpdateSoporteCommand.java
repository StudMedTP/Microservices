package com.studmed.soporte.domain.model.commands;

public record UpdateSoporteCommand(Long id,
                                   String origin,
                                   String destination,
                                   String date) {
}
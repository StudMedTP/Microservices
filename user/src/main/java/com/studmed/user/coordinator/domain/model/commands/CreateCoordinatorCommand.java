package com.studmed.user.coordinator.domain.model.commands;

public record CreateCoordinatorCommand(String coordinatorCode,
                                       Long userId) {}
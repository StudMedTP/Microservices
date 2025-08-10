package com.studmed.user.coordinator.domain.service;

import com.studmed.user.coordinator.domain.model.commands.CreateCoordinatorCommand;

public interface CoordinatorCommandService {
    Long handle(CreateCoordinatorCommand command);
}
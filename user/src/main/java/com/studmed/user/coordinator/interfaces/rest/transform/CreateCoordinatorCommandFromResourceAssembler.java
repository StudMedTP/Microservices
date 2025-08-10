package com.studmed.user.coordinator.interfaces.rest.transform;

import com.studmed.user.coordinator.domain.model.commands.CreateCoordinatorCommand;
import com.studmed.user.coordinator.interfaces.rest.resource.CreateCoordinatorResource;

public class CreateCoordinatorCommandFromResourceAssembler {
    public static CreateCoordinatorCommand toCommandFromResource(CreateCoordinatorResource resource) {
        return new CreateCoordinatorCommand(
                resource.coordinatorCode(),
                resource.userId());
    }
}
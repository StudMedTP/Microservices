package com.studmed.soporte.interfaces.rest.transform;

import com.studmed.soporte.domain.model.commands.CreateSoporteCommand;
import com.studmed.soporte.interfaces.rest.resource.CreateSoporteResource;

public class CreateSoporteCommandFromResourceAssembler {
    public static CreateSoporteCommand toCommandFromResource(CreateSoporteResource resource) {
        return new CreateSoporteCommand(
                resource.origin(),
                resource.destination(),
                resource.date());
    }
}

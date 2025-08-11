package com.studmed.soporte.ticket.interfaces.rest.transform;

import com.studmed.soporte.ticket.domain.model.commands.UpdateSoporteCommand;
import com.studmed.soporte.ticket.interfaces.rest.resource.UpdateSoporteResource;

public class UpdateSoporteCommandFromResourceAssembler {
    public static UpdateSoporteCommand toCommandFromResource(Long id, UpdateSoporteResource resource) {
        return new UpdateSoporteCommand(
                id,
                resource.title(),
                resource.message());
    }
}
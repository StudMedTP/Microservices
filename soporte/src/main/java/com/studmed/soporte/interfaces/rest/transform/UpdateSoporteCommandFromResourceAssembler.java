package com.studmed.soporte.interfaces.rest.transform;

import com.studmed.soporte.domain.model.commands.UpdateSoporteCommand;
import com.studmed.soporte.interfaces.rest.resource.UpdateSoporteResource;

public class UpdateSoporteCommandFromResourceAssembler {
    public static UpdateSoporteCommand toCommandFromResource(Long id, UpdateSoporteResource resource) {
        return new UpdateSoporteCommand(
                id,
                resource.ticketCreationTime(),
                resource.ticketSentTitle(),
                resource.ticketSentMessage(),
                resource.ticketState(),
                resource.ticketResponseTitle(),
                resource.ticketResponseMessage(),
                resource.ticketResponseTime());
    }
}
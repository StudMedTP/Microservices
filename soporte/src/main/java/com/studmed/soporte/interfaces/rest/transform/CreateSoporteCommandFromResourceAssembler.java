package com.studmed.soporte.interfaces.rest.transform;

import com.studmed.soporte.domain.model.commands.CreateSoporteCommand;
import com.studmed.soporte.interfaces.rest.resource.CreateSoporteResource;

public class CreateSoporteCommandFromResourceAssembler {
    public static CreateSoporteCommand toCommandFromResource(CreateSoporteResource resource) {
        return new CreateSoporteCommand(
                resource.ticketCreationTime(),
                resource.ticketSentTitle(),
                resource.ticketSentMessage(),
                resource.ticketState(),
                resource.ticketResponseTitle(),
                resource.ticketResponseMessage(),
                resource.ticketResponseTime());
    }
}
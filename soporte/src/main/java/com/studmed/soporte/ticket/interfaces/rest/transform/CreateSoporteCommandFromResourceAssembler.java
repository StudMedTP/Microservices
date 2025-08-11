package com.studmed.soporte.ticket.interfaces.rest.transform;

import com.studmed.soporte.ticket.domain.model.commands.CreateSoporteCommand;
import com.studmed.soporte.ticket.interfaces.rest.resource.CreateSoporteResource;

public class CreateSoporteCommandFromResourceAssembler {
    public static CreateSoporteCommand toCommandFromResource(CreateSoporteResource resource) {
        return new CreateSoporteCommand(
                resource.title(),
                resource.message(),
                resource.studentId(),
                resource.teacherId());
    }
}
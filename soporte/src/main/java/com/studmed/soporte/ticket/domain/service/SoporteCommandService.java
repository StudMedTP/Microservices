package com.studmed.soporte.ticket.domain.service;

import com.studmed.soporte.ticket.domain.model.commands.CreateSoporteCommand;
import com.studmed.soporte.ticket.domain.model.commands.DeleteSoporteCommand;
import com.studmed.soporte.ticket.domain.model.commands.UpdateSoporteCommand;

public interface SoporteCommandService {
    Long handle (CreateSoporteCommand command);
    Long handle (UpdateSoporteCommand command);
    void handle (DeleteSoporteCommand command);
}
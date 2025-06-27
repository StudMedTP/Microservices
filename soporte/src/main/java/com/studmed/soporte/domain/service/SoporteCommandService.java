package com.studmed.soporte.domain.service;

import com.studmed.soporte.domain.model.aggregates.Soporte;
import com.studmed.soporte.domain.model.commands.CreateSoporteCommand;
import com.studmed.soporte.domain.model.commands.DeleteSoporteCommand;
import com.studmed.soporte.domain.model.commands.UpdateSoporteCommand;

import java.util.Optional;

public interface SoporteCommandService {
    Long handle (CreateSoporteCommand command);
    Optional<Soporte> handle(UpdateSoporteCommand command);
    void handle (DeleteSoporteCommand command);
}
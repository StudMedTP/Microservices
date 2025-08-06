package com.studmed.soporte.application.internal.commandservice;

import com.studmed.soporte.client.UserClient;
import com.studmed.soporte.domain.model.aggregates.Soporte;
import com.studmed.soporte.domain.model.client.User;
import com.studmed.soporte.domain.model.commands.CreateSoporteCommand;
import com.studmed.soporte.domain.model.commands.DeleteSoporteCommand;
import com.studmed.soporte.domain.model.commands.UpdateSoporteCommand;
import com.studmed.soporte.domain.service.SoporteCommandService;
import com.studmed.soporte.infraestructure.persistance.jpa.repositories.SoporteRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SoporteCommandServiceImpl implements SoporteCommandService {

    public final SoporteRepository soporteRepository;

    private final UserClient userClient;

    public SoporteCommandServiceImpl(SoporteRepository soporteRepository, UserClient userClient) {
        this.soporteRepository = soporteRepository;
        this.userClient = userClient;
    }

    @Override
    public Long handle (CreateSoporteCommand command) {
        if(soporteRepository.existsByTicketSentTitle(command.ticketSentTitle())){
            throw new IllegalArgumentException("Soporte already exists");
        }

        try {
            User user = userClient.getUserById(command.userId()).getBody();
            Soporte soporte = new Soporte(command);
            try {
                soporteRepository.save(soporte);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error while saving soporte" + e.getMessage());
            }
            return soporte.getId();
        } catch (FeignException.BadRequest e) {
            throw new RuntimeException("El usuario con ID " + command.userId() + " no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar usuario");
        }
    }

    @Override
    public Optional<Soporte> handle (UpdateSoporteCommand command) {
        if (soporteRepository.existsByTicketSentTitleAndIdIsNot(command.ticketSentTitle(), command.id())){
            throw new IllegalArgumentException("Soporte with same date already exists");
        }

        var result = soporteRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Soporte does not exist");
        }

        var soporteToUpdate = result.get();
        try {
            var updatedSoporte = soporteRepository.save(soporteToUpdate.updateSoporte(
                    command.ticketCreationTime(),
                    command.ticketSentMessage(),
                    command.ticketSentMessage(),
                    command.ticketState(),
                    command.ticketResponseTitle(),
                    command.ticketResponseMessage(),
                    command.ticketResponseTime()));
            return Optional.of(updatedSoporte);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving soporte" + e.getMessage());
        }
    }

    @Override
    public void handle (DeleteSoporteCommand command) {
        if(!soporteRepository.existsById(command.id())){
            throw new IllegalArgumentException("Soporte does not exist");
        }
        try {
            soporteRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving Soporte" + e.getMessage());
        }
    }
}
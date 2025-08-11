package com.studmed.soporte.ticket.application.internal.commandservice;

import com.studmed.soporte.ticket.client.UserClient;
import com.studmed.soporte.ticket.domain.model.aggregates.Soporte;
import com.studmed.soporte.ticket.domain.model.commands.CreateSoporteCommand;
import com.studmed.soporte.ticket.domain.model.commands.DeleteSoporteCommand;
import com.studmed.soporte.ticket.domain.model.commands.UpdateSoporteCommand;
import com.studmed.soporte.ticket.domain.service.SoporteCommandService;
import com.studmed.soporte.ticket.infraestructure.persistance.jpa.repositories.SoporteRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SoporteCommandServiceImpl implements SoporteCommandService {

    public final SoporteRepository soporteRepository;
    public final UserClient userClient;

    public SoporteCommandServiceImpl(SoporteRepository soporteRepository, UserClient userClient) {
        this.soporteRepository = soporteRepository;
        this.userClient = userClient;
    }

    @Override
    public Long handle (CreateSoporteCommand command) {
        if (soporteRepository.existsByTitle(command.title())){
            throw new IllegalArgumentException("Ya existe un ticket con ese título");
        }

        try {
            userClient.getStudentById(command.studentId());
            userClient.getTeacherById(command.teacherId());

            Soporte soporte = new Soporte(command);
            return soporteRepository.save(soporte).getId();
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El estudiante o profesor no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante o profesor.");
        }
    }

    @Override
    public Long handle (UpdateSoporteCommand command) {
        Optional<Soporte> soporteOptional = soporteRepository.findById(command.id());

        if (soporteOptional.isEmpty()){
            throw new IllegalArgumentException("No se encontró soporte");
        }

        if (soporteRepository.existsByTitleAndIdIsNot(command.title(), command.id())){
            throw new IllegalArgumentException("Ya existe un ticket con ese título");
        }

        Soporte soporte = soporteOptional.get();

        soporte.setTitle(command.title());
        soporte.setMessage(command.message());

        return soporteRepository.save(soporte).getId();
    }

    @Override
    public void handle (DeleteSoporteCommand command) {
        if(!soporteRepository.existsById(command.id())){
            throw new IllegalArgumentException("No se encontró soporte");
        }

        soporteRepository.deleteById(command.id());
    }
}
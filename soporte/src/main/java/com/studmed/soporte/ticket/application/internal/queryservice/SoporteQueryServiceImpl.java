package com.studmed.soporte.ticket.application.internal.queryservice;

import com.studmed.soporte.shared.exception.ResourceNotFoundException;
import com.studmed.soporte.ticket.client.UserClient;
import com.studmed.soporte.ticket.domain.model.aggregates.Soporte;
import com.studmed.soporte.ticket.domain.model.queries.GetAllSoporteQuery;
import com.studmed.soporte.ticket.domain.model.queries.GetSoporteByIdQuery;
import com.studmed.soporte.ticket.domain.service.SoporteQueryService;
import com.studmed.soporte.ticket.infraestructure.persistance.jpa.repositories.SoporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoporteQueryServiceImpl implements SoporteQueryService {

    private final SoporteRepository soporteRepository;
    private final UserClient userClient;

    public SoporteQueryServiceImpl(SoporteRepository soporteRepository, UserClient userClient) {
        this.soporteRepository = soporteRepository;
        this.userClient = userClient;
    }

    @Override
    public Soporte handle (GetSoporteByIdQuery query) {
        Optional<Soporte> soporteOptional = soporteRepository.findById(query.id());

        if (soporteOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró usuario");
        }

        Soporte soporte = soporteOptional.get();
        soporte.setStudent(userClient.getStudentById(soporte.getStudentId()).getBody());
        soporte.setTeacher(userClient.getTeacherById(soporte.getTeacherId()).getBody());

        return soporte;
    }

    @Override
    public List<Soporte> handle (GetAllSoporteQuery query) {
        return soporteRepository.findAll();
    }
}
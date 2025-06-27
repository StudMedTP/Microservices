package com.studmed.soporte.application.internal.queryservice;

import com.studmed.soporte.domain.model.aggregates.Soporte;
import com.studmed.soporte.domain.model.queries.GetAllSoporteQuery;
import com.studmed.soporte.domain.model.queries.GetSoporteByIdQuery;
import com.studmed.soporte.domain.service.SoporteQueryService;
import com.studmed.soporte.infraestructure.persistance.jpa.repositories.SoporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoporteQueryServiceImpl implements SoporteQueryService {

    private final SoporteRepository soporteRepository;

    public SoporteQueryServiceImpl(SoporteRepository soporteRepository) {
        this.soporteRepository = soporteRepository;
    }

    @Override
    public Optional<Soporte> handle (GetSoporteByIdQuery query) {
        return soporteRepository.findById(query.id());
    }

    @Override
    public List<Soporte> handle (GetAllSoporteQuery query) {
        return soporteRepository.findAll();
    }
}
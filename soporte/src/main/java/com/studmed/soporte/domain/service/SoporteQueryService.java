package com.studmed.soporte.domain.service;

import com.studmed.soporte.domain.model.aggregates.Soporte;
import com.studmed.soporte.domain.model.queries.GetAllSoporteQuery;
import com.studmed.soporte.domain.model.queries.GetSoporteByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SoporteQueryService {
    List<Soporte> handle (GetAllSoporteQuery query);
    Optional<Soporte> handle (GetSoporteByIdQuery query);
}
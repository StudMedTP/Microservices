package com.studmed.soporte.ticket.domain.service;

import com.studmed.soporte.ticket.domain.model.aggregates.Soporte;
import com.studmed.soporte.ticket.domain.model.queries.GetAllSoporteQuery;
import com.studmed.soporte.ticket.domain.model.queries.GetSoporteByIdQuery;

import java.util.List;

public interface SoporteQueryService {
    List<Soporte> handle (GetAllSoporteQuery query);
    Soporte handle (GetSoporteByIdQuery query);
}
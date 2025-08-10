package com.studmed.user.coordinator.domain.service;

import com.studmed.user.coordinator.domain.model.aggregates.Coordinator;
import com.studmed.user.coordinator.domain.model.queries.GetCoordinatorByIdQuery;

public interface CoordinatorQueryService {
    Coordinator handle(GetCoordinatorByIdQuery query);
}
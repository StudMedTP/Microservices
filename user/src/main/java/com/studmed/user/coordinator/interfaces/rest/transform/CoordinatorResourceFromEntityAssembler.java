package com.studmed.user.coordinator.interfaces.rest.transform;

import com.studmed.user.coordinator.domain.model.aggregates.Coordinator;
import com.studmed.user.coordinator.interfaces.rest.resource.CoordinatorResource;
import com.studmed.user.user.interfaces.rest.resource.UserResource;

public class CoordinatorResourceFromEntityAssembler {
    public static CoordinatorResource toResourceFromEntity(Coordinator entity) {
        return new CoordinatorResource(
                entity.getId(),
                entity.getCoordinatorCode(),
                new UserResource(
                        entity.getUser().getId(),
                        entity.getUser().getFirstName(),
                        entity.getUser().getLastName(),
                        entity.getUser().getEmail(),
                        entity.getUser().getPassword(),
                        entity.getUser().getUserImg()));
    }
}
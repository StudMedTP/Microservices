package com.studmed.soporte.interfaces.rest.transform;

import com.studmed.soporte.domain.model.aggregates.Soporte;
import com.studmed.soporte.interfaces.rest.resource.SoporteResource;

public class SoporteResourceFromEntityAssembler {
    public static SoporteResource toResourceFromEntity(Soporte entity) {
        return new SoporteResource(
                entity.getId(),
                entity.getOrigin(),
                entity.getDestination(),
                entity.getDate());
    }
}

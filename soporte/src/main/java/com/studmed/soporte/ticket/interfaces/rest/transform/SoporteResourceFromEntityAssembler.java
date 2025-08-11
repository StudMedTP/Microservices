package com.studmed.soporte.ticket.interfaces.rest.transform;

import com.studmed.soporte.ticket.domain.model.aggregates.Soporte;
import com.studmed.soporte.ticket.domain.model.client.StudentResource;
import com.studmed.soporte.ticket.interfaces.rest.resource.SoporteResource;

public class SoporteResourceFromEntityAssembler {
    public static SoporteResource toResourceFromEntity(Soporte entity) {
        return new SoporteResource(
                entity.getId(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getStudent(),
                entity.getTeacher(),
                entity.getCreatedAt());
    }
}
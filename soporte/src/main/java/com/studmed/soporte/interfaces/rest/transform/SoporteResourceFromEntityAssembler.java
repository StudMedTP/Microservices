package com.studmed.soporte.interfaces.rest.transform;

import com.studmed.soporte.domain.model.aggregates.Soporte;
import com.studmed.soporte.interfaces.rest.resource.SoporteResource;

public class SoporteResourceFromEntityAssembler {
    public static SoporteResource toResourceFromEntity(Soporte entity) {
        return new SoporteResource(
                entity.getId(),
                entity.getTicketCreationTime(),
                entity.getTicketSentTitle(),
                entity.getTicketSentMessage(),
                entity.getTicketState(),
                entity.getTicketResponseTitle(),
                entity.getTicketResponseMessage(),
                entity.getTicketResponseTime());
    }
}
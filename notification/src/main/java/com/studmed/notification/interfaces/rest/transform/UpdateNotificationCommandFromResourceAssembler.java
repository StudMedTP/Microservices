package com.studmed.notification.interfaces.rest.transform;

import com.studmed.notification.domain.model.commands.UpdateNotificationCommand;
import com.studmed.notification.interfaces.rest.resource.UpdateNotificationResource;

public class UpdateNotificationCommandFromResourceAssembler {
    public static UpdateNotificationCommand toCommandFromResource(Long id, UpdateNotificationResource resource) {
        return new UpdateNotificationCommand(
                id,
                resource.title(),
                resource.message(),
                resource.time(),
                resource.notificationType());
    }
}
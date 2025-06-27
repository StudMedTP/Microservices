package com.studmed.notification.interfaces.rest.transform;


import com.studmed.notification.domain.model.commands.CreateNotificationCommand;
import com.studmed.notification.interfaces.rest.resource.CreateNotificationResource;


public class CreateNotificationCommandFromResourceAssembler {

    public static CreateNotificationCommand toCommandFromResource (CreateNotificationResource resource) {

        return new CreateNotificationCommand(
                resource.title(),
                resource.message(),
                resource.time(),
                resource.notificationType());
    }

}

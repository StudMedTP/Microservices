package com.studmed.notification.interfaces.rest.transform;

import com.studmed.notification.domain.model.aggregates.Notification;
import com.studmed.notification.interfaces.rest.resource.NotificationResource;


public class NotificationResourceFromEntityAssembler {

    public static NotificationResource toResourceFromEntity(Notification entity) {

        return new NotificationResource(
                entity.getId(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getTime(),
                entity.getNotificationType());

    }

}

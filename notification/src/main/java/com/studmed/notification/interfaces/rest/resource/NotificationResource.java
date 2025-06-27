package com.studmed.notification.interfaces.rest.resource;

public record NotificationResource(Long id,
                                   String title,
                                   String message,
                                   String time,
                                   String notificationType) {

}

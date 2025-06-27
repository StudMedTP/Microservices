package com.studmed.notification.interfaces.rest.resource;

public record CreateNotificationResource(String title,
                                         String message,
                                         String time,
                                         String notificationType) {

}

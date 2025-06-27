package com.studmed.notification.interfaces.rest.resource;

public record UpdateNotificationResource(String title,
                                         String message,
                                         String time,
                                         String notificationType) {

}
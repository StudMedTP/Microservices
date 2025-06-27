package com.studmed.notification.domain.model.commands;

public record CreateNotificationCommand(String title,
                                        String message,
                                        String time,
                                        String notificationType) {

}
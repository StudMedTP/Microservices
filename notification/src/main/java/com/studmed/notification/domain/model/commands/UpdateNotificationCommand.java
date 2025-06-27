package com.studmed.notification.domain.model.commands;

public record UpdateNotificationCommand(Long id,
                                        String title,
                                        String message,
                                        String time,
                                        String notificationType){

}
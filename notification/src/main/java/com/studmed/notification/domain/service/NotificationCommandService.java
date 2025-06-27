package com.studmed.notification.domain.service;

import com.studmed.notification.domain.model.aggregates.Notification;
import com.studmed.notification.domain.model.commands.CreateNotificationCommand;
import com.studmed.notification.domain.model.commands.DeleteNotificationCommand;
import com.studmed.notification.domain.model.commands.UpdateNotificationCommand;

import java.util.Optional;


public interface NotificationCommandService {

    Long handle (CreateNotificationCommand command);
    Optional<Notification> handle (UpdateNotificationCommand command);
    void handle (DeleteNotificationCommand command);

}
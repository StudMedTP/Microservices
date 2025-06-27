package com.studmed.notification.application.internal.commandservice;

import com.studmed.notification.domain.model.aggregates.Notification;
import com.studmed.notification.domain.model.commands.CreateNotificationCommand;
import com.studmed.notification.domain.model.commands.DeleteNotificationCommand;
import com.studmed.notification.domain.model.commands.UpdateNotificationCommand;
import com.studmed.notification.domain.service.NotificationCommandService;
import com.studmed.notification.infraestructure.persistance.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    public final NotificationRepository notificationRepository;
    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Long handle (CreateNotificationCommand command) {
        if (notificationRepository.existsByTitle(command.title())){
            throw new IllegalArgumentException("Notification already exists");
        }

        Notification notification = new Notification(command);
        try {
            notificationRepository.save(notification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving trip" + e.getMessage());
        }
        return notification.getId();
    }

    @Override
    public Optional<Notification> handle(UpdateNotificationCommand command) {
        if (notificationRepository.existsByTitleAndIdIsNot(command.title(), command.id())){
            throw new IllegalArgumentException("Notification with same title already exists");
        }

        var result = notificationRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Notification does not exist");
        }

        var notificationToUpdate = result.get();
        try {
            var updatedNotification = notificationRepository.save(notificationToUpdate.updateNotification(
                    command.title(),
                    command.message(),
                    command.time(),
                    command.notificationType()
            ));
            return Optional.of(updatedNotification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving notification" + e.getMessage());
        }
    }

    @Override
    public void handle (DeleteNotificationCommand command) {
        if(!notificationRepository.existsById(command.id())){
            throw new IllegalArgumentException("Notification does not exist");
        }
        try {
            notificationRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting notification" + e.getMessage());
        }
    }
}
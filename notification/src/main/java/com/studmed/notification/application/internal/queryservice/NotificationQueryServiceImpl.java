package com.studmed.notification.application.internal.queryservice;


import com.studmed.notification.domain.model.aggregates.Notification;
import com.studmed.notification.domain.model.queries.GetAllNotificationQuery;
import com.studmed.notification.domain.model.queries.GetNotificationByIdQuery;
import com.studmed.notification.domain.service.NotificationQueryService;
import com.studmed.notification.infraestructure.persistance.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service


public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(GetNotificationByIdQuery query) {
        return notificationRepository.findById(query.id());
    }

    @Override
    public List<Notification> handle(GetAllNotificationQuery query) {
        return notificationRepository.findAll();
    }

}
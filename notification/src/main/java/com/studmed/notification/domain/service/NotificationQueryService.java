package com.studmed.notification.domain.service;

import com.studmed.notification.domain.model.aggregates.Notification;
import com.studmed.notification.domain.model.queries.GetAllNotificationQuery;
import com.studmed.notification.domain.model.queries.GetNotificationByIdQuery;

import java.util.List;
import java.util.Optional;


public interface NotificationQueryService {

    List<Notification> handle (GetAllNotificationQuery query);
    Optional<Notification> handle (GetNotificationByIdQuery query);

}
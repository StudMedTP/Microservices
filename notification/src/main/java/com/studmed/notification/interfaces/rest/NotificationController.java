package com.studmed.notification.interfaces.rest;

import com.studmed.notification.domain.model.commands.DeleteNotificationCommand;
import com.studmed.notification.domain.model.queries.GetAllNotificationQuery;
import com.studmed.notification.domain.model.queries.GetNotificationByIdQuery;
import com.studmed.notification.domain.service.NotificationCommandService;
import com.studmed.notification.domain.service.NotificationQueryService;
import com.studmed.notification.interfaces.rest.resource.NotificationResource;
import com.studmed.notification.interfaces.rest.resource.CreateNotificationResource;
import com.studmed.notification.interfaces.rest.resource.UpdateNotificationResource;
import com.studmed.notification.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import com.studmed.notification.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import com.studmed.notification.interfaces.rest.transform.UpdateNotificationCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/notifications", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Notification", description = "Notification Management Endpoints")
public class NotificationController {

    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource createNotificationResource) {

        var createNotificationCommand = CreateNotificationCommandFromResourceAssembler.toCommandFromResource(createNotificationResource);
        var id = notificationCommandService.handle(createNotificationCommand);
        if (id == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getNotificationByIdQuery = new GetNotificationByIdQuery(id);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if (notification.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return new ResponseEntity<>(notificationResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResource>> getAllNotifications() {
        var getAllNotificationQuery = new GetAllNotificationQuery();
        var notification = notificationQueryService.handle(getAllNotificationQuery);
        var notificationResource = notification.stream().map(NotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(notificationResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResource> getNotificationById(@PathVariable Long id) {
        var getNotificationByIdQuery = new GetNotificationByIdQuery(id);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if (notification.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return ResponseEntity.ok(notificationResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationResource> updateNotification(@PathVariable Long id, @RequestBody UpdateNotificationResource updateNotificationResource) {
        var updateNotificationCommand = UpdateNotificationCommandFromResourceAssembler.toCommandFromResource(id, updateNotificationResource);
        var updateNotification = notificationCommandService.handle(updateNotificationCommand);
        if (updateNotification.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(updateNotification.get());
        return ResponseEntity.ok(notificationResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        var deleteNotificationCommand = new DeleteNotificationCommand(id);
        notificationCommandService.handle(deleteNotificationCommand);
        return ResponseEntity.ok("Notification with given id successfully deleted");
    }
}
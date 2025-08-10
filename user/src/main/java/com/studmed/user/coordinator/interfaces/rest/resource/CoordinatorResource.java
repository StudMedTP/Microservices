package com.studmed.user.coordinator.interfaces.rest.resource;

import com.studmed.user.user.interfaces.rest.resource.UserResource;

public record CoordinatorResource(
        Long id,
        String coordinatorCode,
        UserResource userResource) {}


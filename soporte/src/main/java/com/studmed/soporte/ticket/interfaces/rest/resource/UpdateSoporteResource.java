package com.studmed.soporte.ticket.interfaces.rest.resource;

import jakarta.validation.constraints.NotBlank;

public record UpdateSoporteResource(
        @NotBlank()
        String title,
        @NotBlank()
        String message) {}
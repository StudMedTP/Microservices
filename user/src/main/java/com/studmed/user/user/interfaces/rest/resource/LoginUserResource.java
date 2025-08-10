package com.studmed.user.user.interfaces.rest.resource;

import jakarta.validation.constraints.NotBlank;

public record LoginUserResource(
        @NotBlank()
        String email,
        @NotBlank()
        String password) {}
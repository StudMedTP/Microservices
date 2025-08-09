package com.studmed.user.user.interfaces.rest.resource;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserResource (
        @NotBlank()
        String firstName,
        @NotBlank()
        String lastName,
        @NotBlank()
        String email,
        @NotBlank()
        String password,
        @NotBlank()
        String userImg) {}
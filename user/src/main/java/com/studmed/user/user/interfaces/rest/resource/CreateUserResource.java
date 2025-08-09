package com.studmed.user.user.interfaces.rest.resource;

import jakarta.validation.constraints.NotBlank;

public record CreateUserResource (
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
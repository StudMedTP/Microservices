package com.studmed.user.user.interfaces.rest.resource;

public record UserResource (
        Long id,
        String firstName,
        String lastName,
        String email,
        String password,
        String userImg) {}
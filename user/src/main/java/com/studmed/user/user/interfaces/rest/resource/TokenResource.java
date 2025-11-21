package com.studmed.user.user.interfaces.rest.resource;

public record TokenResource(
        String token,
        String role) {}
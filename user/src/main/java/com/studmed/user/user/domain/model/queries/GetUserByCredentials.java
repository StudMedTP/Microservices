package com.studmed.user.user.domain.model.queries;

public record GetUserByCredentials(String email,
                                   String password) {}

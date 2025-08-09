package com.studmed.user.user.domain.model.commands;

public record UpdateUserCommand (Long id,
                                 String firstName,
                                 String lastName,
                                 String email,
                                 String password,
                                 String userImg) {}
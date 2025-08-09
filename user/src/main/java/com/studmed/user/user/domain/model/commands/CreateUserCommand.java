package com.studmed.user.user.domain.model.commands;

public record CreateUserCommand (String firstName,
                                 String lastName,
                                 String email,
                                 String password,
                                 String userImg) {}
package com.studmed.user.domain.model.commands;

public record CreateUserCommand (String firstName,
                                String lastName,
                                String email,
                                String userName,
                                String password,
                                String phoneNumber){
}
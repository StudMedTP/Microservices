package com.studmed.user.domain.model.commands;

public record CreateUserCommand (String rol,
                                 String firstName,
                                 String lastName,
                                 String email,
                                 String userName,
                                 String password,
                                 String phoneNumber,
                                 String userImg){
}
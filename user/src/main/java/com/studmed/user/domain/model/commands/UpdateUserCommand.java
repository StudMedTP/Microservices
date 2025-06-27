package com.studmed.user.domain.model.commands;

public record UpdateUserCommand (Long id,
                                 String rol,
                                 String firstName,
                                 String lastName,
                                 String email,
                                 String userName,
                                 String password,
                                 String phoneNumber,
                                 String userImg){
}
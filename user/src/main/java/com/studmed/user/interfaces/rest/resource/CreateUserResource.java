package com.studmed.user.interfaces.rest.resource;

public record CreateUserResource (String rol,
                                  String firstName,
                                  String lastName,
                                  String email,
                                  String userName,
                                  String password,
                                  String phoneNumber,
                                  String userImg) {
}
package com.studmed.user.user.interfaces.rest.resource;

public record UpdateUserResource (String rol,
                                  String firstName,
                                  String lastName,
                                  String email,
                                  String userName,
                                  String password,
                                  String phoneNumber,
                                  String userImg){
}
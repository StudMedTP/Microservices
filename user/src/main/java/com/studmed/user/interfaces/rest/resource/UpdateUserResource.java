package com.studmed.user.interfaces.rest.resource;

public record UpdateUserResource (String firstName,
                                  String lastName,
                                  String email,
                                  String userName,
                                  String password,
                                  String phoneNumber){
}
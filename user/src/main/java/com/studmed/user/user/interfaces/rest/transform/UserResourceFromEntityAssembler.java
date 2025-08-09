package com.studmed.user.user.interfaces.rest.transform;

import com.studmed.user.user.domain.model.aggregates.User;
import com.studmed.user.user.interfaces.rest.resource.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        return new UserResource(
                entity.getId(),
                entity.getRol(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getPhoneNumber(),
                entity.getUserImg());
    }
}
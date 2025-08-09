package com.studmed.user.user.interfaces.rest.transform;

import com.studmed.user.user.domain.model.commands.CreateUserCommand;
import com.studmed.user.user.interfaces.rest.resource.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.rol(),
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.userName(),
                resource.password(),
                resource.phoneNumber(),
                resource.userImg());
    }
}
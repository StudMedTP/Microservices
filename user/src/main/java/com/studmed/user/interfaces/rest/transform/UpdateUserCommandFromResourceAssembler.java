package com.studmed.user.interfaces.rest.transform;

import com.studmed.user.domain.model.commands.UpdateUserCommand;
import com.studmed.user.interfaces.rest.resource.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long id, UpdateUserResource resource) {
        return new UpdateUserCommand(
                id,
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
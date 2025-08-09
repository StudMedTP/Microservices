package com.studmed.user.user.interfaces.rest.transform;

import com.studmed.user.user.domain.model.commands.UpdateUserCommand;
import com.studmed.user.user.interfaces.rest.resource.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long id, UpdateUserResource resource) {
        return new UpdateUserCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.password(),
                resource.userImg());
    }
}
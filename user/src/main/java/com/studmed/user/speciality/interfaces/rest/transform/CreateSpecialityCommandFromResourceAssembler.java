package com.studmed.user.speciality.interfaces.rest.transform;

import com.studmed.user.speciality.domain.model.commands.CreateSpecialityCommand;
import com.studmed.user.speciality.interfaces.rest.resource.CreateSpecialityResource;

public class CreateSpecialityCommandFromResourceAssembler {
    public static CreateSpecialityCommand toCommandFromResource(CreateSpecialityResource resource) {
        return new CreateSpecialityCommand(
                resource.name());
    }
}
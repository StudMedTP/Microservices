package com.studmed.user.medical_center.interfaces.rest.transform;

import com.studmed.user.medical_center.domain.model.commands.CreateMedicalCenterCommand;
import com.studmed.user.medical_center.interfaces.rest.resource.CreateMedicalCenterResource;

public class CreateMedicalCenterCommandFromResourceAssembler {
    public static CreateMedicalCenterCommand toCommandFromResource(CreateMedicalCenterResource resource) {
        return new CreateMedicalCenterCommand(
                resource.name());
    }
}
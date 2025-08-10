package com.studmed.user.medical_center.interfaces.rest.transform;

import com.studmed.user.medical_center.domain.model.aggregates.MedicalCenter;
import com.studmed.user.medical_center.interfaces.rest.resource.MedicalCenterResource;

public class MedicalCenterResourceFromEntityAssembler {
    public static MedicalCenterResource toResourceFromEntity(MedicalCenter entity) {
        return new MedicalCenterResource(
                entity.getId(),
                entity.getName());
    }
}
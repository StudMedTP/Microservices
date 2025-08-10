package com.studmed.user.speciality.interfaces.rest.transform;

import com.studmed.user.speciality.domain.model.aggregates.Speciality;
import com.studmed.user.speciality.interfaces.rest.resource.SpecialityResource;

public class SpecialityResourceFromEntityAssembler {
    public static SpecialityResource toResourceFromEntity(Speciality entity) {
        return new SpecialityResource(
                entity.getId(),
                entity.getName());
    }
}
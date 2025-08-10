package com.studmed.user.teacher.interfaces.rest.transform;

import com.studmed.user.teacher.domain.model.commands.CreateTeacherCommand;
import com.studmed.user.teacher.interfaces.rest.resource.CreateTeacherResource;

public class CreateTeacherCommandFromResourceAssembler {
    public static CreateTeacherCommand toCommandFromResource(CreateTeacherResource resource) {
        return new CreateTeacherCommand(
                resource.teacherCode(),
                resource.userId(),
                resource.medicalCenterId(),
                resource.specialityId(),
                resource.coordinatorId());
    }
}
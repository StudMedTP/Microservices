package com.studmed.evaluation.classroom.interfaces.rest.transform;

import com.studmed.evaluation.classroom.domain.model.commands.CreateClassroomCommand;
import com.studmed.evaluation.classroom.interfaces.rest.resource.CreateClassroomResource;

public class CreateClassroomCommandFromResourceAssembler {
    public static CreateClassroomCommand toCommandFromResource(CreateClassroomResource resource) {
        return new CreateClassroomCommand(
                resource.name(),
                resource.medicalCenterId(),
                resource.teacherId());
    }
}
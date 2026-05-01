package com.studmed.evaluation.grade.interfaces.rest.transform;

import com.studmed.evaluation.grade.domain.model.commands.CreateGradeCommand;
import com.studmed.evaluation.grade.interfaces.rest.resource.CreateGradeResource;

public class CreateGradeCommandFromResourceAssembler {
    public static CreateGradeCommand toCommandFromResource(CreateGradeResource resource) {
        return new CreateGradeCommand(
                resource.classroomStudentId(),
                resource.value(),
                resource.description());
    }
}
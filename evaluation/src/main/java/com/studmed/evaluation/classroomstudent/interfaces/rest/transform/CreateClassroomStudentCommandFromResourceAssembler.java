package com.studmed.evaluation.classroomstudent.interfaces.rest.transform;

import com.studmed.evaluation.classroomstudent.domain.model.commands.CreateClassroomStudentCommand;
import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.CreateClassroomStudentResource;

public class CreateClassroomStudentCommandFromResourceAssembler {
    public static CreateClassroomStudentCommand toCommandFromResource(CreateClassroomStudentResource resource) {
        return new CreateClassroomStudentCommand(
                resource.studentId(),
                resource.classroomId());
    }
}
package com.studmed.user.student.intefaces.rest.transform;

import com.studmed.user.student.domain.model.commands.CreateStudentCommand;
import com.studmed.user.student.intefaces.rest.resource.CreateStudentResource;

public class CreateStudentCommandFromResourceAssembler {
    public static CreateStudentCommand toCommandFromResource(CreateStudentResource resource) {
        return new CreateStudentCommand(
                resource.studentCode());
    }
}
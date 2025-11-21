package com.studmed.user.teacher.interfaces.rest.transform;

import com.studmed.user.teacher.domain.model.commands.OpenClassByIdCommand;

public class OpenClassByIdCommandFromResourceAssembler {
    public static OpenClassByIdCommand toCommandFromResource(Long teacherId) {
        return new OpenClassByIdCommand(
                teacherId);
    }
}
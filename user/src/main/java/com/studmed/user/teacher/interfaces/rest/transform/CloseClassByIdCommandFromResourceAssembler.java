package com.studmed.user.teacher.interfaces.rest.transform;

import com.studmed.user.teacher.domain.model.commands.CloseClassByIdCommand;

public class CloseClassByIdCommandFromResourceAssembler {
    public static CloseClassByIdCommand toCommandFromResource(Long teacherId) {
        return new CloseClassByIdCommand(
                teacherId);
    }
}
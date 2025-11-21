package com.studmed.user.teacher.domain.service;

import com.studmed.user.teacher.domain.model.commands.CloseClassByIdCommand;
import com.studmed.user.teacher.domain.model.commands.CreateTeacherCommand;
import com.studmed.user.teacher.domain.model.commands.OpenClassByIdCommand;

public interface TeacherCommandService {
    Long handle(CreateTeacherCommand command);
    Long handle(OpenClassByIdCommand command);
    Long handle(CloseClassByIdCommand command);
}
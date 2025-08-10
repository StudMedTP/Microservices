package com.studmed.user.teacher.domain.service;

import com.studmed.user.teacher.domain.model.commands.CreateTeacherCommand;

public interface TeacherCommandService {
    Long handle(CreateTeacherCommand command);
}
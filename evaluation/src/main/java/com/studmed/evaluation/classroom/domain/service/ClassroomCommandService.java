package com.studmed.evaluation.classroom.domain.service;

import com.studmed.evaluation.classroom.domain.model.commands.CreateClassroomCommand;

public interface ClassroomCommandService {
    Long handle (CreateClassroomCommand command);
}
package com.studmed.evaluation.classroomstudent.domain.service;

import com.studmed.evaluation.classroomstudent.domain.model.commands.CreateClassroomStudentCommand;

public interface ClassroomStudentCommandService {
    Long handle (CreateClassroomStudentCommand command);
}
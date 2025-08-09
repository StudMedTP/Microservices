package com.studmed.user.student.domain.service;

import com.studmed.user.student.domain.model.commands.CreateStudentCommand;

public interface StudentCommandService {
    Long handle(CreateStudentCommand command);
}
package com.studmed.evaluation.grade.domain.service;

import com.studmed.evaluation.grade.domain.model.commands.CreateGradeCommand;

public interface GradeCommandService {
    Long handle (CreateGradeCommand command);
}
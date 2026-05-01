package com.studmed.evaluation.grade.domain.model.commands;

public record CreateGradeCommand(Long classStudentId,
                                 Long value,
                                 String description) {}
package com.studmed.user.student.domain.model.commands;

public record CreateStudentCommand(String studentCode,
                                   Long userId) {}
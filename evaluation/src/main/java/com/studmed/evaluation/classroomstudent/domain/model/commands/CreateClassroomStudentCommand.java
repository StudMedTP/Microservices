package com.studmed.evaluation.classroomstudent.domain.model.commands;

public record CreateClassroomStudentCommand(Long studentId,
                                            Long classroomId) {}
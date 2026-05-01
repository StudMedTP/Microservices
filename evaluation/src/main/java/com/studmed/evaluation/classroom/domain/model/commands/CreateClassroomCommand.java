package com.studmed.evaluation.classroom.domain.model.commands;

public record CreateClassroomCommand(String name,
                                     Long medicalCenterId,
                                     Long teacherId) {}
package com.studmed.user.teacher.domain.model.commands;

public record CreateTeacherCommand(String teacherCode,
                                   Long userId,
                                   Long medicalCenterId,
                                   Long specialityId,
                                   Long coordinatorId) {}
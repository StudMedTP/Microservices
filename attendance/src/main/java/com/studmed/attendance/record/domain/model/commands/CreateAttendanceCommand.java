package com.studmed.attendance.record.domain.model.commands;

public record CreateAttendanceCommand(Long studentId,
                                      Long medicalCenterId,
                                      String status) {}
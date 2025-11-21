package com.studmed.attendance.record.domain.model.commands;

public record UpdateAttendanceCommand(Long id,
                                      String status) {}
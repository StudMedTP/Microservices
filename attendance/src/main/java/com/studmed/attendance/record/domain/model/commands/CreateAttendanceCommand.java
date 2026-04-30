package com.studmed.attendance.record.domain.model.commands;

public record CreateAttendanceCommand(Long studentId,
                                      Long teacherId,
                                      Double latitude,
                                      Double longitude) {}
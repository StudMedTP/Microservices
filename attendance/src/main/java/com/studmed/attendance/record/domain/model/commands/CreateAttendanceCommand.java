package com.studmed.attendance.record.domain.model.commands;

public record CreateAttendanceCommand(Long studentId,
                                      Long teacherId,
                                      Long classroomId,
                                      Double latitude,
                                      Double longitude) {}
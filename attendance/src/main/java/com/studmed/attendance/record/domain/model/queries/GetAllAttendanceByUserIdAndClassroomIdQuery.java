package com.studmed.attendance.record.domain.model.queries;

public record GetAllAttendanceByUserIdAndClassroomIdQuery(Long userId,
                                                          Long classroomId) {}
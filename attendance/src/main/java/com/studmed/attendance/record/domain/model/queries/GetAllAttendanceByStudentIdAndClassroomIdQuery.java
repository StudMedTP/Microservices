package com.studmed.attendance.record.domain.model.queries;

public record GetAllAttendanceByStudentIdAndClassroomIdQuery(Long studentId,
                                                             Long classroomId) {}
package com.studmed.attendance.record.domain.service;

import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceByStudentIdAndClassroomIdQuery;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceByUserIdAndClassroomIdQuery;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceQuery;
import com.studmed.attendance.record.domain.model.queries.GetAttendanceByIdQuery;

import java.util.List;

public interface AttendanceQueryService {
    List<Attendance> handle (GetAllAttendanceQuery query);
    Attendance handle (GetAttendanceByIdQuery query);
    List<Attendance> handle (GetAllAttendanceByStudentIdAndClassroomIdQuery query);
    List<Attendance> handle (GetAllAttendanceByUserIdAndClassroomIdQuery query);
}
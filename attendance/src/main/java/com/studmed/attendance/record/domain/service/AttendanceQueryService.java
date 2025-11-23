package com.studmed.attendance.record.domain.service;

import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceByUserIdQuery;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceQuery;
import com.studmed.attendance.record.domain.model.queries.GetAttendanceByIdQuery;
import com.studmed.attendance.record.domain.model.queries.GetLastAttendanceByStudentIdQuery;

import java.util.List;

public interface AttendanceQueryService {
    List<Attendance> handle (GetAllAttendanceQuery query);
    Attendance handle (GetAttendanceByIdQuery query);
    List<Attendance> handle (GetAllAttendanceByUserIdQuery query);
    Attendance handle (GetLastAttendanceByStudentIdQuery query);
}
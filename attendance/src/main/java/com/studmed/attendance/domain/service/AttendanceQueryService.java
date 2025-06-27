package com.studmed.attendance.domain.service;

import com.studmed.attendance.domain.model.aggregates.Attendance;
import com.studmed.attendance.domain.model.queries.GetAllAttendanceQuery;
import com.studmed.attendance.domain.model.queries.GetAttendanceByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AttendanceQueryService {
    List<Attendance> handle (GetAllAttendanceQuery query);
    Optional<Attendance> handle (GetAttendanceByIdQuery query);
}
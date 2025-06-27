package com.studmed.attendance.domain.service;

import com.studmed.attendance.domain.model.aggregates.Attendance;
import com.studmed.attendance.domain.model.commands.CreateAttendanceCommand;
import com.studmed.attendance.domain.model.commands.DeleteAttendanceCommand;
import com.studmed.attendance.domain.model.commands.UpdateAttendanceCommand;

import java.util.Optional;

public interface AttendanceCommandService {
    Long handle (CreateAttendanceCommand command);
    Optional<Attendance> handle (UpdateAttendanceCommand command);
    void handle (DeleteAttendanceCommand command);
}
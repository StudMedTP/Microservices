package com.studmed.attendance.record.domain.service;

import com.studmed.attendance.record.domain.model.commands.CreateAttendanceCommand;
import com.studmed.attendance.record.domain.model.commands.DeleteAttendanceCommand;
import com.studmed.attendance.record.domain.model.commands.UpdateAttendanceCommand;

public interface AttendanceCommandService {
    Long handle (CreateAttendanceCommand command);
    Long handle (UpdateAttendanceCommand command);
    void handle (DeleteAttendanceCommand command);
}
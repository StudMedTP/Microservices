package com.studmed.attendance.record.interfaces.rest.transform;

import com.studmed.attendance.record.domain.model.commands.CreateAttendanceCommand;
import com.studmed.attendance.record.interfaces.rest.resource.CreateAttendanceResource;

public class CreateAttendanceCommandFromResourceAssembler {
    public static CreateAttendanceCommand toCommandFromResource(CreateAttendanceResource resource) {
        return new CreateAttendanceCommand(
                resource.studentId(),
                resource.medicalCenterId(),
                "PENDIENTE",
                resource.date());
    }
}
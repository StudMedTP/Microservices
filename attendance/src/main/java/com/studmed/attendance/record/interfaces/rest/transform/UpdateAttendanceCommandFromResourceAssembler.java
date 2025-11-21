package com.studmed.attendance.record.interfaces.rest.transform;

import com.studmed.attendance.record.domain.model.commands.UpdateAttendanceCommand;
import com.studmed.attendance.record.interfaces.rest.resource.UpdateAttendanceResource;

public class UpdateAttendanceCommandFromResourceAssembler {
    public static UpdateAttendanceCommand toCommandFromResource(Long id, UpdateAttendanceResource resource){
        return new UpdateAttendanceCommand(
                id,
                resource.status());
    }
}
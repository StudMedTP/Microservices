package com.studmed.attendance.record.interfaces.rest.transform;

import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.interfaces.rest.resource.AttendanceResource;

public class AttendanceResourceFromEntityAssembler {
    public static AttendanceResource toResourceFromEntity(Attendance entity) {
        return new AttendanceResource(
                entity.getId(),
                entity.getStudentId(),
                entity.getMedicalCenterId(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
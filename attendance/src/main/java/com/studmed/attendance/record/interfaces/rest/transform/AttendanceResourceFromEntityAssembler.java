package com.studmed.attendance.record.interfaces.rest.transform;

import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.interfaces.rest.resource.AttendanceResource;
import com.studmed.attendance.record.interfaces.rest.resource.AttendanceResourcePlain;
import com.studmed.attendance.record.interfaces.rest.resource.MedicalCenterResource;
import com.studmed.attendance.record.interfaces.rest.resource.StudentResource;

public class AttendanceResourceFromEntityAssembler {
    public static AttendanceResource toResourcePlainFromEntity(Attendance entity) {
        return new AttendanceResource(
                entity.getId(),
                entity.getStudentId(),
                entity.getMedicalCenterId(),
                entity.getStatus(),
                new StudentResource(
                        entity.getStudent().getId(),
                        entity.getStudent().getStudentCode(),
                        entity.getStudent().getTeacherId()
                ),
                new MedicalCenterResource(
                        entity.getMedicalCenter().getId(),
                        entity.getMedicalCenter().getName()
                ),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public static AttendanceResourcePlain toResourceFromEntity(Attendance entity) {
        return new AttendanceResourcePlain(
                entity.getId(),
                entity.getStudentId(),
                entity.getMedicalCenterId(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
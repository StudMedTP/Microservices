package com.studmed.attendance.record.interfaces.rest.transform;

import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.interfaces.rest.resource.*;

public class AttendanceResourceFromEntityAssembler {
    public static AttendanceResource toResourceFromEntity(Attendance entity) {
        return new AttendanceResource(
                entity.getId(),
                entity.getStudentId(),
                entity.getTeacherId(),
                new StudentResource(
                        entity.getStudent().getId(),
                        entity.getStudent().getStudentCode()
                ),
                new TeacherResource(
                        entity.getTeacher().getId(),
                        entity.getTeacher().getTeacherCode(),
                        new UserResource(
                                entity.getTeacher().getUserResource().getId(),
                                entity.getTeacher().getUserResource().getFirstName(),
                                entity.getTeacher().getUserResource().getLastName()
                        )
                ),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getIsPartial());
    }

    public static AttendanceResourcePlain toResourcePlainFromEntity(Attendance entity) {
        return new AttendanceResourcePlain(
                entity.getId(),
                entity.getStudentId(),
                entity.getTeacherId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getIsPartial());
    }
}
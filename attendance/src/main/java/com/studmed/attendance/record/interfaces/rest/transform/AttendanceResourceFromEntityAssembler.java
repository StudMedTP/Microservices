package com.studmed.attendance.record.interfaces.rest.transform;

import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.interfaces.rest.resource.AttendanceResource;
import com.studmed.attendance.record.interfaces.rest.resource.AttendanceResourcePlain;
import com.studmed.attendance.record.interfaces.rest.resource.StudentResource;
import com.studmed.attendance.record.interfaces.rest.resource.TeacherResource;

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
                        entity.getTeacher().getTeacherCode()
                ),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getLatitude(),
                entity.getLongitude());
    }

    public static AttendanceResourcePlain toResourcePlainFromEntity(Attendance entity) {
        return new AttendanceResourcePlain(
                entity.getId(),
                entity.getStudentId(),
                entity.getTeacherId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getLatitude(),
                entity.getLongitude());
    }
}
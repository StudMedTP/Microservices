package com.studmed.attendance.interfaces.rest.transform;

import com.studmed.attendance.domain.model.aggregates.Attendance;
import com.studmed.attendance.interfaces.rest.resource.AttendanceResource;

public class AttendanceResourceFromEntityAssembler {
    public static AttendanceResource toResourceFromEntity(Attendance entity) {
        return new AttendanceResource(
                entity.getId(),
                entity.getAttendaceDate(),
                entity.getRegistrationTime(),
                entity.getCourseName(),
                entity.getAttendaceState(),
                entity.getVerificationToken(),
                entity.getCoordinates());
    }
}
package com.studmed.attendance.interfaces.rest.resource;

public record UpdateAttendanceResource(String attendaceDate,
                                       String registrationTime,
                                       String courseName,
                                       String attendaceState,
                                       String verificationToken,
                                       String coordinates) {
}
package com.studmed.attendance.domain.model.commands;

public record CreateAttendanceCommand(String attendaceDate,
                                      String registrationTime,
                                      String courseName,
                                      String attendaceState,
                                      String verificationToken,
                                      String coordinates) {
}
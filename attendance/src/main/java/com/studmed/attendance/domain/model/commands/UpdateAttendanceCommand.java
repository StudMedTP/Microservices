package com.studmed.attendance.domain.model.commands;

public record UpdateAttendanceCommand(Long id,
                                      String attendaceDate,
                                      String registrationTime,
                                      String courseName,
                                      String attendaceState,
                                      String verificationToken,
                                      String coordinates) {
}
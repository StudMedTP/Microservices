package com.studmed.attendance.record.domain.model.commands;

import java.time.LocalDateTime;

public record CreateAttendanceCommand(Long studentId,
                                      Long medicalCenterId,
                                      String status,
                                      LocalDateTime date) {}
package com.studmed.attendance.record.interfaces.rest.resource;

import java.time.LocalDateTime;
import java.util.Date;

public record AttendanceResourcePlain(Long id,
                                      Long studentId,
                                      Long medicalCenterId,
                                      String status,
                                      Date createdAt,
                                      Date updatedAt,
                                      LocalDateTime date) {}
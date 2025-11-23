package com.studmed.attendance.record.interfaces.rest.resource;

import java.util.Date;

public record AttendanceResourcePlain(Long id,
                                      Long studentId,
                                      Long medicalCenterId,
                                      String status,
                                      Date createdAt,
                                      Date updatedAt) {}
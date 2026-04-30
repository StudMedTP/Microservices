package com.studmed.attendance.record.interfaces.rest.resource;

import java.util.Date;

public record AttendanceResourcePlain(Long id,
                                      Long studentId,
                                      Long teacherId,
                                      Date createdAt,
                                      Date updatedAt,
                                      Double latitude,
                                      Double longitude) {}
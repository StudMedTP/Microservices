package com.studmed.attendance.record.interfaces.rest.resource;

import java.util.Date;

public record AttendanceResource(Long id,
                                 Long studentId,
                                 Long teacherId,
                                 StudentResource studentResource,
                                 TeacherResource teacherResource,
                                 Date createdAt,
                                 Date updatedAt,
                                 Double latitude,
                                 Double longitude) {}
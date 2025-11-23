package com.studmed.attendance.record.interfaces.rest.resource;

import java.time.LocalDateTime;
import java.util.Date;

public record AttendanceResource(Long id,
                                 Long studentId,
                                 Long medicalCenterId,
                                 String status,
                                 StudentResource studentResource,
                                 MedicalCenterResource medicalCenterResource,
                                 Date createdAt,
                                 Date updatedAt,
                                 LocalDateTime date,
                                 Double latitude,
                                 Double longitude) {}
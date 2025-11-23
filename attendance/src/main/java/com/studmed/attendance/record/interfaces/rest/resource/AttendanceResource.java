package com.studmed.attendance.record.interfaces.rest.resource;

import java.util.Date;

public record AttendanceResource(Long id,
                                 Long studentId,
                                 Long medicalCenterId,
                                 String status,
                                 StudentResource studentResource,
                                 MedicalCenterResource medicalCenterResource,
                                 Date createdAt,
                                 Date updatedAt) {}
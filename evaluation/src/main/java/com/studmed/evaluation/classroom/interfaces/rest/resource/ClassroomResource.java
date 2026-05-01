package com.studmed.evaluation.classroom.interfaces.rest.resource;

import java.util.Date;

public record ClassroomResource(Long id,
                                String name,
                                Long medicalCenterId,
                                Long teacherId,
                                MedicalCenterResource medicalCenterResource,
                                TeacherResource teacherResource,
                                Date createdAt,
                                Date updatedAt) {}
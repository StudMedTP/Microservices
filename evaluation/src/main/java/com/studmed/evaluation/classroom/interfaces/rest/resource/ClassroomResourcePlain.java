package com.studmed.evaluation.classroom.interfaces.rest.resource;

import java.util.Date;

public record ClassroomResourcePlain(Long id,
                                     String name,
                                     Long medicalCenterId,
                                     Long teacherId,
                                     Date createdAt,
                                     Date updatedAt) {}
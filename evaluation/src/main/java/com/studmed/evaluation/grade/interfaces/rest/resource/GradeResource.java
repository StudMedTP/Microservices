package com.studmed.evaluation.grade.interfaces.rest.resource;

import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.ClassroomStudentResourcePlain;

import java.util.Date;

public record GradeResource(Long id,
                            Long value,
                            String description,
                            Date createdAt,
                            ClassroomStudentResourcePlain classroomStudentResource) {}
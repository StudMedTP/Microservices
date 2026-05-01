package com.studmed.evaluation.grade.interfaces.rest.resource;

import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.ClassroomStudentResourcePlain;

public record GradeResource(Long id,
                            Long value,
                            String description,
                            ClassroomStudentResourcePlain classroomStudentResource) {}
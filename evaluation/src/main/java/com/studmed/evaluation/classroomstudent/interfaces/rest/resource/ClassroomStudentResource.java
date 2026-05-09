package com.studmed.evaluation.classroomstudent.interfaces.rest.resource;

import com.studmed.evaluation.classroom.interfaces.rest.resource.ClassroomResource;

public record ClassroomStudentResource(Long id,
                                       Long studentId,
                                       StudentResource studentResource,
                                       ClassroomResource classroomResource) {}
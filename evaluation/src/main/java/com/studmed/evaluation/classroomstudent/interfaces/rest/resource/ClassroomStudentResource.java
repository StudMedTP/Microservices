package com.studmed.evaluation.classroomstudent.interfaces.rest.resource;

import com.studmed.evaluation.classroom.interfaces.rest.resource.ClassroomResourcePlain;

public record ClassroomStudentResource(Long id,
                                       Long studentId,
                                       StudentResource studentResource,
                                       ClassroomResourcePlain classroomResource) {}
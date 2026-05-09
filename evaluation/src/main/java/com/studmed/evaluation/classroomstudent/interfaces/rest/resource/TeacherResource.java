package com.studmed.evaluation.classroomstudent.interfaces.rest.resource;

import com.studmed.evaluation.classroom.interfaces.rest.resource.UserResource;

public record TeacherResource(
        Long id,
        String teacherCode,
        UserResource userResource
) {}

package com.studmed.user.student.intefaces.rest.resource;

import com.studmed.user.teacher.interfaces.rest.resource.TeacherResourcePlain;
import com.studmed.user.user.interfaces.rest.resource.UserResource;

public record StudentResource(
        Long id,
        String studentCode,
        UserResource userResource,
        TeacherResourcePlain teacherResource) {}
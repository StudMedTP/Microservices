package com.studmed.user.student.intefaces.rest.transform;

import com.studmed.user.student.domain.model.aggregates.Student;
import com.studmed.user.student.intefaces.rest.resource.StudentResource;
import com.studmed.user.user.interfaces.rest.resource.UserResource;

public class StudentResourceFromEntityAssembler {
    public static StudentResource toResourceFromEntity(Student entity) {
        return new StudentResource(
                entity.getId(),
                entity.getStudentCode(),
                new UserResource(
                        entity.getUser().getId(),
                        entity.getUser().getFirstName(),
                        entity.getUser().getLastName(),
                        entity.getUser().getEmail(),
                        entity.getUser().getPassword(),
                        entity.getUser().getUserImg()));
    }
}
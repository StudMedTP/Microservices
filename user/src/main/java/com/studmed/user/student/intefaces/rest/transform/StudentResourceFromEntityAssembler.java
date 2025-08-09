package com.studmed.user.student.intefaces.rest.transform;

import com.studmed.user.student.domain.model.aggregates.Student;
import com.studmed.user.student.intefaces.rest.resource.StudentResource;

public class StudentResourceFromEntityAssembler {
    public static StudentResource toResourceFromEntity(Student entity){
        return new StudentResource(
                entity.getId(),
                entity.getStudentCode());
    }
}
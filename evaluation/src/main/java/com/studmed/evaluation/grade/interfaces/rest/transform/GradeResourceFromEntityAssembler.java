package com.studmed.evaluation.grade.interfaces.rest.transform;

import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.ClassroomStudentResourcePlain;
import com.studmed.evaluation.grade.domain.model.aggregate.Grade;
import com.studmed.evaluation.grade.interfaces.rest.resource.GradeResource;
import com.studmed.evaluation.grade.interfaces.rest.resource.GradeResourcePlain;

public class GradeResourceFromEntityAssembler {
    public static GradeResource toResourceFromEntity(Grade entity) {
        return new GradeResource(
                entity.getId(),
                entity.getValue(),
                entity.getDescription(),
                new ClassroomStudentResourcePlain(
                        entity.getClassroomStudent().getId(),
                        entity.getClassroomStudent().getClassroom().getId(),
                        entity.getClassroomStudent().getStudentId()
                ));
    }

    public static GradeResourcePlain toResourcePlainFromEntity(Grade entity) {
        return new GradeResourcePlain(
                entity.getId(),
                entity.getValue(),
                entity.getDescription());
    }
}
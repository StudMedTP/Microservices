package com.studmed.evaluation.classroomstudent.interfaces.rest.transform;

import com.studmed.evaluation.classroom.interfaces.rest.resource.ClassroomResourcePlain;
import com.studmed.evaluation.classroomstudent.domain.model.aggregate.ClassroomStudent;
import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.ClassroomStudentResource;
import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.ClassroomStudentResourcePlain;
import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.StudentResource;

public class ClassroomStudentResourceFromEntityAssembler {
    public static ClassroomStudentResource toResourceFromEntity(ClassroomStudent entity) {
        return new ClassroomStudentResource(
                entity.getId(),
                entity.getStudentId(),
                new StudentResource(
                        entity.getStudent().getId(),
                        entity.getStudent().getStudentCode()
                ),
                new ClassroomResourcePlain(
                        entity.getClassroom().getId(),
                        entity.getClassroom().getName(),
                        entity.getClassroom().getMedicalCenterId(),
                        entity.getClassroom().getTeacherId(),
                        entity.getClassroom().getCreatedAt(),
                        entity.getClassroom().getUpdatedAt()
                ));
    }

    public static ClassroomStudentResourcePlain toResourcePlainFromEntity(ClassroomStudent entity) {
        return new ClassroomStudentResourcePlain(
                entity.getId(),
                entity.getClassroom().getId(),
                entity.getStudentId());
    }
}
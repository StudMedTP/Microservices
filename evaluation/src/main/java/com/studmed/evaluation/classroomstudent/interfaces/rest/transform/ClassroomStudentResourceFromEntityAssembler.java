package com.studmed.evaluation.classroomstudent.interfaces.rest.transform;

import com.studmed.evaluation.classroom.interfaces.rest.resource.ClassroomResource;
import com.studmed.evaluation.classroom.interfaces.rest.resource.MedicalCenterResource;
import com.studmed.evaluation.classroom.interfaces.rest.resource.TeacherResource;
import com.studmed.evaluation.classroomstudent.domain.model.aggregate.ClassroomStudent;
import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.ClassroomStudentResource;
import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.ClassroomStudentResourcePlain;
import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.StudentResource;
import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.UserResource;

public class ClassroomStudentResourceFromEntityAssembler {
    public static ClassroomStudentResource toResourceFromEntity(ClassroomStudent entity) {
        return new ClassroomStudentResource(
                entity.getId(),
                entity.getStudentId(),
                new StudentResource(
                        entity.getStudent().getId(),
                        entity.getStudent().getStudentCode(),
                        new UserResource(
                                entity.getStudent().getUserResource().getId(),
                                entity.getStudent().getUserResource().getFirstName(),
                                entity.getStudent().getUserResource().getLastName()
                        )
                ),
                new ClassroomResource(
                        entity.getClassroom().getId(),
                        entity.getClassroom().getName(),
                        entity.getClassroom().getMedicalCenterId(),
                        entity.getClassroom().getTeacherId(),
                        new MedicalCenterResource(
                                entity.getClassroom().getMedicalCenter().getId(),
                                entity.getClassroom().getMedicalCenter().getName(),
                                entity.getClassroom().getMedicalCenter().getLatitude(),
                                entity.getClassroom().getMedicalCenter().getLongitude()
                        ),
                        new TeacherResource(
                                entity.getClassroom().getTeacher().getId(),
                                entity.getClassroom().getTeacher().getTeacherCode(),
                                new UserResource(
                                        entity.getClassroom().getTeacher().getUserResource().getId(),
                                        entity.getClassroom().getTeacher().getUserResource().getFirstName(),
                                        entity.getClassroom().getTeacher().getUserResource().getLastName()
                                )
                        ),
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
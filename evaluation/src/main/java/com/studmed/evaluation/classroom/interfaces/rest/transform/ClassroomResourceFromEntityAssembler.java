package com.studmed.evaluation.classroom.interfaces.rest.transform;

import com.studmed.evaluation.classroom.domain.model.aggregate.Classroom;
import com.studmed.evaluation.classroom.interfaces.rest.resource.ClassroomResource;
import com.studmed.evaluation.classroom.interfaces.rest.resource.ClassroomResourcePlain;
import com.studmed.evaluation.classroom.interfaces.rest.resource.MedicalCenterResource;
import com.studmed.evaluation.classroom.interfaces.rest.resource.TeacherResource;
import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.UserResource;

public class ClassroomResourceFromEntityAssembler {
    public static ClassroomResource toResourceFromEntity(Classroom entity) {
        return new ClassroomResource(
                entity.getId(),
                entity.getName(),
                entity.getMedicalCenterId(),
                entity.getTeacherId(),
                new MedicalCenterResource(
                        entity.getMedicalCenter().getId(),
                        entity.getMedicalCenter().getName(),
                        entity.getMedicalCenter().getLatitude(),
                        entity.getMedicalCenter().getLongitude()
                ),
                new TeacherResource(
                        entity.getTeacher().getId(),
                        entity.getTeacher().getTeacherCode(),
                        new UserResource(
                                entity.getTeacher().getUserResource().getId(),
                                entity.getTeacher().getUserResource().getFirstName(),
                                entity.getTeacher().getUserResource().getLastName()
                        )
                ),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public static ClassroomResourcePlain toResourcePlainFromEntity(Classroom entity) {
        return new ClassroomResourcePlain(
                entity.getId(),
                entity.getName(),
                entity.getMedicalCenterId(),
                entity.getTeacherId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
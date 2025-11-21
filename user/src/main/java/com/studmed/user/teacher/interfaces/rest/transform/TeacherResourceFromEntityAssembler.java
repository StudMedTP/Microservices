package com.studmed.user.teacher.interfaces.rest.transform;

import com.studmed.user.coordinator.interfaces.rest.resource.CoordinatorResourcePlain;
import com.studmed.user.medical_center.interfaces.rest.resource.MedicalCenterResource;
import com.studmed.user.speciality.interfaces.rest.resource.SpecialityResource;
import com.studmed.user.teacher.domain.model.aggregates.Teacher;
import com.studmed.user.teacher.interfaces.rest.resource.TeacherResource;
import com.studmed.user.user.interfaces.rest.resource.UserResource;

public class TeacherResourceFromEntityAssembler {
    public static TeacherResource toResourceFromEntity(Teacher entity) {
        return new TeacherResource(
                entity.getId(),
                entity.getTeacherCode(),
                entity.getDailyCode(),
                new UserResource(
                        entity.getUser().getId(),
                        entity.getUser().getFirstName(),
                        entity.getUser().getLastName(),
                        entity.getUser().getEmail(),
                        entity.getUser().getPassword(),
                        entity.getUser().getRole()),
                new MedicalCenterResource(
                        entity.getMedicalCenter().getId(),
                        entity.getMedicalCenter().getName()),
                new SpecialityResource(
                        entity.getSpeciality().getId(),
                        entity.getSpeciality().getName()),
                new CoordinatorResourcePlain(
                        entity.getCoordinator().getId(),
                        entity.getCoordinator().getCoordinatorCode()));
    }
}
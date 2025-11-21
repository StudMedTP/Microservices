package com.studmed.user.teacher.interfaces.rest.resource;

import com.studmed.user.coordinator.interfaces.rest.resource.CoordinatorResourcePlain;
import com.studmed.user.medical_center.interfaces.rest.resource.MedicalCenterResource;
import com.studmed.user.speciality.interfaces.rest.resource.SpecialityResource;
import com.studmed.user.user.interfaces.rest.resource.UserResource;

public record TeacherResource(
        Long id,
        String teacherCode,
        String dailyCode,
        UserResource userResource,
        MedicalCenterResource medicalCenterResource,
        SpecialityResource specialityResource,
        CoordinatorResourcePlain coordinatorResource) {}


package com.studmed.user.medical_center.domain.service;

import com.studmed.user.medical_center.domain.model.commands.CreateMedicalCenterCommand;

public interface MedicalCenterCommandService {
    Long handle(CreateMedicalCenterCommand command);
}
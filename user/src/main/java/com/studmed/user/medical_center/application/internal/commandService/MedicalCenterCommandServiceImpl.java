package com.studmed.user.medical_center.application.internal.commandService;

import com.studmed.user.medical_center.domain.model.aggregates.MedicalCenter;
import com.studmed.user.medical_center.domain.model.commands.CreateMedicalCenterCommand;
import com.studmed.user.medical_center.domain.service.MedicalCenterCommandService;
import com.studmed.user.medical_center.infraestructure.persistance.jpa.respositories.MedicalCenterRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicalCenterCommandServiceImpl implements MedicalCenterCommandService {

    private final MedicalCenterRepository medicalCenterRepository;

    public MedicalCenterCommandServiceImpl(MedicalCenterRepository medicalCenterRepository) {
        this.medicalCenterRepository = medicalCenterRepository;
    }

    @Override
    public Long handle(CreateMedicalCenterCommand command) {
        if (medicalCenterRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Ya existe un centro médico con ese nombre");
        }

        MedicalCenter medicalCenter = new MedicalCenter(command);
        return medicalCenterRepository.save(medicalCenter).getId();
    }
}
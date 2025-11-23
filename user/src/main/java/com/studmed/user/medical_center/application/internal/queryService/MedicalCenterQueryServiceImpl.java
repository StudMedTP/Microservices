package com.studmed.user.medical_center.application.internal.queryService;

import com.studmed.user.medical_center.domain.model.queries.GetAllMedicalCenters;
import com.studmed.user.shared.exception.ResourceNotFoundException;
import com.studmed.user.medical_center.domain.model.aggregates.MedicalCenter;
import com.studmed.user.medical_center.domain.model.queries.GetMedicalCenterByIdQuery;
import com.studmed.user.medical_center.domain.service.MedicalCenterQueryService;
import com.studmed.user.medical_center.infraestructure.persistance.jpa.respositories.MedicalCenterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalCenterQueryServiceImpl implements MedicalCenterQueryService {

    private final MedicalCenterRepository medicalCenterRepository;

    public MedicalCenterQueryServiceImpl(MedicalCenterRepository medicalCenterRepository) {
        this.medicalCenterRepository = medicalCenterRepository;
    }

    @Override
    public List<MedicalCenter> handle(GetAllMedicalCenters query) {
        return medicalCenterRepository.findAll();
    }

    @Override
    public MedicalCenter handle(GetMedicalCenterByIdQuery query) {
        Optional<MedicalCenter> medicalCenterOptional = medicalCenterRepository.findById(query.id());

        if (medicalCenterOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró centro médico");
        }

        return medicalCenterOptional.get();
    }
}
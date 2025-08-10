package com.studmed.user.speciality.application.internal.queryService;

import com.studmed.user.shared.exception.ResourceNotFoundException;
import com.studmed.user.speciality.domain.model.aggregates.Speciality;
import com.studmed.user.speciality.domain.model.queries.GetSpecialityByIdQuery;
import com.studmed.user.speciality.domain.service.SpecialityQueryService;
import com.studmed.user.speciality.infraestructure.persistance.jpa.respositories.SpecialityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpecialityQueryServiceImpl implements SpecialityQueryService {

    private final SpecialityRepository specialityRepository;

    public SpecialityQueryServiceImpl(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Speciality handle(GetSpecialityByIdQuery query) {
        Optional<Speciality> specialityOptional = specialityRepository.findById(query.id());

        if (specialityOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró especialidad");
        }

        return specialityOptional.get();
    }
}
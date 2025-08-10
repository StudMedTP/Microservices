package com.studmed.user.speciality.application.internal.commandService;

import com.studmed.user.speciality.domain.model.aggregates.Speciality;
import com.studmed.user.speciality.domain.model.commands.CreateSpecialityCommand;
import com.studmed.user.speciality.domain.service.SpecialityCommandService;
import com.studmed.user.speciality.infraestructure.persistance.jpa.respositories.SpecialityRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecialityCommandServiceImpl implements SpecialityCommandService {

    private final SpecialityRepository specialityRepository;

    public SpecialityCommandServiceImpl(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Long handle(CreateSpecialityCommand command) {
        if (specialityRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Ya existe una especialidad con ese nombre");
        }

        Speciality speciality = new Speciality(command);
        return specialityRepository.save(speciality).getId();
    }
}
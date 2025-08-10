package com.studmed.user.speciality.domain.service;

import com.studmed.user.speciality.domain.model.commands.CreateSpecialityCommand;

public interface SpecialityCommandService {
    Long handle(CreateSpecialityCommand command);
}
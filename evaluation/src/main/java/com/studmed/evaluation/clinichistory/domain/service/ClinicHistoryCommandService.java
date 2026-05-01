package com.studmed.evaluation.clinichistory.domain.service;

import com.studmed.evaluation.clinichistory.domain.model.commands.CreateClinicHistoryCommand;

public interface ClinicHistoryCommandService {
    Long handle(CreateClinicHistoryCommand command);
}

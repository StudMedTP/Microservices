package com.studmed.evaluation.clinichistory.application.internal.commandservice;

import com.studmed.evaluation.classroom.client.UserClient;
import com.studmed.evaluation.clinichistory.domain.model.aggregate.ClinicHistory;
import com.studmed.evaluation.clinichistory.domain.model.commands.CreateClinicHistoryCommand;
import com.studmed.evaluation.clinichistory.domain.service.ClinicHistoryCommandService;
import com.studmed.evaluation.clinichistory.infraestructure.persistance.jpa.repositories.ClinicHistoryRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class ClinicHistoryCommandServiceImpl implements ClinicHistoryCommandService {

    private final ClinicHistoryRepository clinicHistoryRepository;
    public final UserClient userClient;

    public ClinicHistoryCommandServiceImpl(ClinicHistoryRepository clinicHistoryRepository, UserClient userClient) {
        this.clinicHistoryRepository = clinicHistoryRepository;
        this.userClient = userClient;
    }

    @Override
    public Long handle(CreateClinicHistoryCommand command) {
        try {
            userClient.getStudentById(command.studentId());

            ClinicHistory clinicHistory = new ClinicHistory(command);

            return clinicHistoryRepository.save(clinicHistory).getId();
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El estudiante no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante.");
        }
    }
}

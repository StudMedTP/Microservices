package com.studmed.evaluation.clinichistory.application.internal.queryservice;

import com.studmed.evaluation.classroom.client.UserClient;
import com.studmed.evaluation.classroom.domain.model.client.StudentResource;
import com.studmed.evaluation.clinichistory.domain.model.aggregate.ClinicHistory;
import com.studmed.evaluation.clinichistory.domain.model.queries.GetAllClinicHistoriesByUserIdQuery;
import com.studmed.evaluation.clinichistory.domain.model.queries.GetClinicHistoryByIdQuery;
import com.studmed.evaluation.clinichistory.domain.service.ClinicHistoryQueryService;
import com.studmed.evaluation.clinichistory.infraestructure.persistance.jpa.repositories.ClinicHistoryRepository;
import com.studmed.evaluation.shared.exception.ResourceNotFoundException;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClinicHistoryQueryServiceImpl implements ClinicHistoryQueryService {

    private final ClinicHistoryRepository clinicHistoryRepository;
    public final UserClient userClient;

    public ClinicHistoryQueryServiceImpl(ClinicHistoryRepository clinicHistoryRepository, UserClient userClient) {
        this.clinicHistoryRepository = clinicHistoryRepository;
        this.userClient = userClient;
    }

    @Override
    public ClinicHistory handle(GetClinicHistoryByIdQuery query) {
        Optional<ClinicHistory> clinicHistoryOptional = clinicHistoryRepository.findById(query.id());

        if (clinicHistoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró historial clínico");
        }

        ClinicHistory clinicHistory = clinicHistoryOptional.get();

        try {
            StudentResource studentResource = userClient.getStudentById(clinicHistory.getStudentId()).getBody();
            clinicHistory.setStudent(studentResource);
        } catch (Exception e) {
            StudentResource studentResource = userClient.getStudentById(clinicHistory.getStudentId()).getBody();
            clinicHistory.setStudent(studentResource);
        }

        return clinicHistory;
    }

    @Override
    public List<ClinicHistory> handle(GetAllClinicHistoriesByUserIdQuery query) {
        try {
            Map<String, StudentResource> studentMapResource = userClient.getStudentByUserId(query.userId()).getBody();

            if (studentMapResource != null) {
                List<ClinicHistory> clinicHistories = clinicHistoryRepository.findAllByStudentId(studentMapResource.get("student").getId());

                clinicHistories.forEach((clinicHistory) -> clinicHistory.setStudent(studentMapResource.get("student")));
                return clinicHistories;
            } else {
                throw new RuntimeException("Error al validar estudiante.");
            }
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("El estudiante no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante.");
        }
    }
}

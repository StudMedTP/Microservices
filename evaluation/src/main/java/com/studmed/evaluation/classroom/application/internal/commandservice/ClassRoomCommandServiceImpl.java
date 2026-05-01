package com.studmed.evaluation.classroom.application.internal.commandservice;

import com.studmed.evaluation.classroom.client.UserClient;
import com.studmed.evaluation.classroom.domain.model.aggregate.Classroom;
import com.studmed.evaluation.classroom.domain.model.commands.CreateClassroomCommand;
import com.studmed.evaluation.classroom.domain.service.ClassroomCommandService;
import com.studmed.evaluation.classroom.infraestructure.persistance.jpa.repositories.ClassroomRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class ClassRoomCommandServiceImpl implements ClassroomCommandService {

    private final ClassroomRepository classroomRepository;
    public final UserClient userClient;

    public ClassRoomCommandServiceImpl(ClassroomRepository classroomRepository, UserClient userClient) {
        this.classroomRepository = classroomRepository;
        this.userClient = userClient;
    }

    @Override
    public Long handle(CreateClassroomCommand command) {
        if (classroomRepository.existsByNameAndMedicalCenterId(command.name(), command.medicalCenterId())) {
            throw new IllegalArgumentException("Ya existe una clase en ese centro médico con ese nombre");
        }

        try {
            userClient.getTeacherById(command.teacherId());
            userClient.getMedicalCenterById(command.medicalCenterId());

            Classroom attendance =  new Classroom(command);

            return classroomRepository.save(attendance).getId();
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El centro médico o profesor no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar centro médico o profesor.");
        }
    }
}
package com.studmed.evaluation.classroomstudent.application.internal.commandservice;

import com.studmed.evaluation.classroom.client.UserClient;
import com.studmed.evaluation.classroom.domain.model.aggregate.Classroom;
import com.studmed.evaluation.classroom.infraestructure.persistance.jpa.repositories.ClassroomRepository;
import com.studmed.evaluation.classroomstudent.domain.model.aggregate.ClassroomStudent;
import com.studmed.evaluation.classroomstudent.domain.model.commands.CreateClassroomStudentCommand;
import com.studmed.evaluation.classroomstudent.domain.service.ClassroomStudentCommandService;
import com.studmed.evaluation.classroomstudent.infraestructure.persistance.jpa.repositories.ClassroomStudentRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassroomStudentCommandServiceImpl implements ClassroomStudentCommandService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomStudentRepository classroomStudentRepository;
    public final UserClient userClient;

    public ClassroomStudentCommandServiceImpl(ClassroomRepository classroomRepository, ClassroomStudentRepository classroomStudentRepository,
                                              UserClient userClient) {
        this.classroomRepository = classroomRepository;
        this.classroomStudentRepository = classroomStudentRepository;
        this.userClient = userClient;
    }

    @Override
    public Long handle(CreateClassroomStudentCommand command) {
        if (classroomStudentRepository.existsByClassroom_IdAndStudentId(command.classroomId(), command.studentId())) {
            throw new IllegalArgumentException("Ya existe ese estudiante en la clase");
        }

        Optional<Classroom> classroomOptional = classroomRepository.findById(command.classroomId());

        if (classroomOptional.isEmpty()) {
            throw new IllegalArgumentException("Existe un error en los datos ingresados");
        }

        try {
            userClient.getStudentById(command.studentId());

            ClassroomStudent classroomStudent =  new ClassroomStudent(command, classroomOptional.get());

            return classroomStudentRepository.save(classroomStudent).getId();
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El estudiante no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante.");
        }
    }
}
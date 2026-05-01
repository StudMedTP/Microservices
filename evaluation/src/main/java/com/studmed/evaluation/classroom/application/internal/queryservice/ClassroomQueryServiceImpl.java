package com.studmed.evaluation.classroom.application.internal.queryservice;

import com.studmed.evaluation.classroom.client.UserClient;
import com.studmed.evaluation.classroom.domain.model.aggregate.Classroom;
import com.studmed.evaluation.classroom.domain.model.client.MedicalCenterResource;
import com.studmed.evaluation.classroom.domain.model.client.TeacherResource;
import com.studmed.evaluation.classroom.domain.model.queries.GetAllClassroomByUserIdQuery;
import com.studmed.evaluation.classroom.domain.model.queries.GetClassroomByIdQuery;
import com.studmed.evaluation.classroom.domain.service.ClassroomQueryService;
import com.studmed.evaluation.classroom.infraestructure.persistance.jpa.repositories.ClassroomRepository;
import com.studmed.evaluation.shared.exception.ResourceNotFoundException;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomQueryServiceImpl implements ClassroomQueryService {

    private final ClassroomRepository classroomRepository;
    public final UserClient userClient;

    public ClassroomQueryServiceImpl(ClassroomRepository classroomRepository, UserClient userClient) {
        this.classroomRepository = classroomRepository;
        this.userClient = userClient;
    }

    @Override
    public Classroom handle (GetClassroomByIdQuery query){
        Optional<Classroom> classroomOptional = classroomRepository.findById(query.id());

        if (classroomOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró clase");
        }

        Classroom classroom = classroomOptional.get();

        try {
            MedicalCenterResource medicalCenterResourceClassroom = userClient.getMedicalCenterById(classroom.getMedicalCenterId()).getBody();
            classroom.setMedicalCenter(medicalCenterResourceClassroom);
            TeacherResource teacherResource = userClient.getTeacherById(classroom.getTeacherId()).getBody();
            classroom.setTeacher(teacherResource);
        } catch (Exception e) {
            MedicalCenterResource medicalCenterResourceClassroom = MedicalCenterResource.builder().build();
            classroom.setMedicalCenter(medicalCenterResourceClassroom);
            TeacherResource teacherResource = userClient.getTeacherById(classroom.getTeacherId()).getBody();
            classroom.setTeacher(teacherResource);
        }

        return classroom;
    }

    @Override
    public List<Classroom> handle (GetAllClassroomByUserIdQuery query){
        try {
            TeacherResource teacherResource = userClient.getTeacherByUserId(query.userId()).getBody();

            if (teacherResource != null) {
                List<Classroom> classrooms = classroomRepository.findAllByTeacherId(teacherResource.getId());

                classrooms.forEach((classroom) -> {
                    try {
                        MedicalCenterResource medicalCenterResourceClassroom = userClient.getMedicalCenterById(classroom.getMedicalCenterId()).getBody();
                        classroom.setMedicalCenter(medicalCenterResourceClassroom);
                        classroom.setTeacher(teacherResource);
                    } catch (Exception e) {
                        MedicalCenterResource medicalCenterResourceClassroom = MedicalCenterResource.builder().build();
                        classroom.setMedicalCenter(medicalCenterResourceClassroom);
                        classroom.setTeacher(teacherResource);
                    }
                });
                return classrooms;
            } else {
                throw new RuntimeException("Error al validar profesor.");
            }
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("El profesor no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar profesor.");
        }
    }
}
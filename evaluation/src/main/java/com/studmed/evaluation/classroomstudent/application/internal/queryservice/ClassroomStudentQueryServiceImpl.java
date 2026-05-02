package com.studmed.evaluation.classroomstudent.application.internal.queryservice;

import com.studmed.evaluation.classroom.client.UserClient;
import com.studmed.evaluation.classroom.domain.model.client.StudentResource;
import com.studmed.evaluation.classroom.infraestructure.persistance.jpa.repositories.ClassroomRepository;
import com.studmed.evaluation.classroomstudent.domain.model.aggregate.ClassroomStudent;
import com.studmed.evaluation.classroomstudent.domain.model.queries.GetAllClassroomStudentByClassIdQuery;
import com.studmed.evaluation.classroomstudent.domain.model.queries.GetAllClassroomStudentByUserIdQuery;
import com.studmed.evaluation.classroomstudent.domain.model.queries.GetClassroomStudentByIdQuery;
import com.studmed.evaluation.classroomstudent.domain.service.ClassroomStudentQueryService;
import com.studmed.evaluation.classroomstudent.infraestructure.persistance.jpa.repositories.ClassroomStudentRepository;
import com.studmed.evaluation.shared.exception.ResourceNotFoundException;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClassroomStudentQueryServiceImpl implements ClassroomStudentQueryService {

    private final ClassroomStudentRepository classroomStudentRepository;
    private final ClassroomRepository classroomRepository;
    public final UserClient userClient;

    public ClassroomStudentQueryServiceImpl(ClassroomStudentRepository classroomStudentRepository, ClassroomRepository classroomRepository,
                                            UserClient userClient) {
        this.classroomStudentRepository = classroomStudentRepository;
        this.classroomRepository = classroomRepository;
        this.userClient = userClient;
    }

    @Override
    public ClassroomStudent handle (GetClassroomStudentByIdQuery query){
        Optional<ClassroomStudent> classroomStudentOptional = classroomStudentRepository.findById(query.id());

        if (classroomStudentOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró estudiante en clase");
        }

        return classroomStudentOptional.get();
    }

    @Override
    public List<ClassroomStudent> handle (GetAllClassroomStudentByUserIdQuery query){
        try {
            Map<String, StudentResource> studentMapResource = userClient.getStudentByUserId(query.userId()).getBody();

            if (studentMapResource != null) {
                List<ClassroomStudent> classroomStudents = classroomStudentRepository.findAllByStudentId(studentMapResource.get("student").getId());

                classroomStudents.forEach((classroomStudent) -> {
                    classroomStudent.setStudent(studentMapResource.get("student"));
                });
                return classroomStudents;
            } else {
                throw new RuntimeException("Error al validar estudiante.");
            }
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("El estudiante no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante.");
        }
    }

    @Override
    public List<ClassroomStudent> handle (GetAllClassroomStudentByClassIdQuery query) {
        List<ClassroomStudent> classroomStudents = classroomStudentRepository.findAllByClassroom_Id(query.classId());

        classroomStudents.forEach((classroomStudent) -> {
            try {
                StudentResource studentResource = userClient.getStudentById(classroomStudent.getStudentId()).getBody();
                classroomStudent.setStudent(studentResource);
            } catch (Exception e) {
                StudentResource studentResource = StudentResource.builder().build();
                classroomStudent.setStudent(studentResource);
            }

            classroomRepository.findById(classroomStudent.getClassroom().getId()).ifPresent(classroomStudent::setClassroom);
        });
        return classroomStudents;
    }
}
package com.studmed.evaluation.grade.application.internal.queryservice;

import com.studmed.evaluation.classroom.client.UserClient;
import com.studmed.evaluation.classroom.domain.model.client.StudentResource;
import com.studmed.evaluation.classroom.infraestructure.persistance.jpa.repositories.ClassroomRepository;
import com.studmed.evaluation.classroomstudent.domain.model.aggregate.ClassroomStudent;
import com.studmed.evaluation.classroomstudent.domain.model.queries.GetAllClassroomStudentByClassIdQuery;
import com.studmed.evaluation.classroomstudent.domain.model.queries.GetAllClassroomStudentByUserIdQuery;
import com.studmed.evaluation.classroomstudent.domain.model.queries.GetClassroomStudentByIdQuery;
import com.studmed.evaluation.classroomstudent.domain.service.ClassroomStudentQueryService;
import com.studmed.evaluation.classroomstudent.infraestructure.persistance.jpa.repositories.ClassroomStudentRepository;
import com.studmed.evaluation.grade.domain.model.aggregate.Grade;
import com.studmed.evaluation.grade.domain.model.queries.GetAllGradeByClassStudentIdQuery;
import com.studmed.evaluation.grade.domain.model.queries.GetGradeByIdQuery;
import com.studmed.evaluation.grade.domain.service.GradeQueryService;
import com.studmed.evaluation.grade.infraestructure.persistance.jpa.repositories.GradeRepository;
import com.studmed.evaluation.shared.exception.ResourceNotFoundException;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeQueryServiceImpl implements GradeQueryService {

    private final ClassroomStudentRepository classroomStudentRepository;
    private final GradeRepository gradeRepository;
    public final UserClient userClient;

    public GradeQueryServiceImpl(ClassroomStudentRepository classroomStudentRepository, GradeRepository gradeRepository,
                                 UserClient userClient) {
        this.classroomStudentRepository = classroomStudentRepository;
        this.gradeRepository = gradeRepository;
        this.userClient = userClient;
    }

    @Override
    public Grade handle (GetGradeByIdQuery query) {
        Optional<Grade> gradeOptional = gradeRepository.findById(query.id());

        if (gradeOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró calificación");
        }

        return gradeOptional.get();
    }

    @Override
    public List<Grade> handle (GetAllGradeByClassStudentIdQuery query) {
        List<Grade> grades = gradeRepository.findAllByClassroomStudent_Id(query.classStudentId());

        grades.forEach((grade) -> classroomStudentRepository.findById(query.classStudentId()).ifPresent(grade::setClassroomStudent));

        return grades;
    }
}
package com.studmed.evaluation.grade.application.internal.commandservice;

import com.studmed.evaluation.classroom.client.UserClient;
import com.studmed.evaluation.classroomstudent.domain.model.aggregate.ClassroomStudent;
import com.studmed.evaluation.classroomstudent.infraestructure.persistance.jpa.repositories.ClassroomStudentRepository;
import com.studmed.evaluation.grade.domain.model.aggregate.Grade;
import com.studmed.evaluation.grade.domain.model.commands.CreateGradeCommand;
import com.studmed.evaluation.grade.domain.service.GradeCommandService;
import com.studmed.evaluation.grade.infraestructure.persistance.jpa.repositories.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeCommandServiceImpl implements GradeCommandService {

    private final GradeRepository gradeRepository;
    private final ClassroomStudentRepository classroomStudentRepository;
    public final UserClient userClient;

    public GradeCommandServiceImpl(GradeRepository gradeRepository, ClassroomStudentRepository classroomStudentRepository,
                                   UserClient userClient) {
        this.gradeRepository = gradeRepository;
        this.classroomStudentRepository = classroomStudentRepository;
        this.userClient = userClient;
    }

    @Override
    public Long handle(CreateGradeCommand command) {
        Optional<ClassroomStudent> classroomStudentOptional = classroomStudentRepository.findById(command.classStudentId());

        if (classroomStudentOptional.isEmpty()) {
            throw new IllegalArgumentException("Existe un error en los datos ingresados");
        }

        Grade grade = new Grade(command, classroomStudentOptional.get());
        return gradeRepository.save(grade).getId();
    }
}
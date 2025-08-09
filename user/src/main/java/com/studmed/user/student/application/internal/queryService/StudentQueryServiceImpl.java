package com.studmed.user.student.application.internal.queryService;

import com.studmed.user.student.domain.model.aggregates.Student;
import com.studmed.user.student.domain.model.queries.GetStudentByIdQuery;
import com.studmed.user.student.domain.service.StudentQueryService;
import com.studmed.user.student.infraestructure.persistance.jpa.respositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentQueryServiceImpl implements StudentQueryService {

    private final StudentRepository studentRepository;

    public StudentQueryServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> handle(GetStudentByIdQuery query) {
        return studentRepository.findById(query.id());
    }
}
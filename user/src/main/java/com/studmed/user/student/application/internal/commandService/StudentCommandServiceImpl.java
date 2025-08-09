package com.studmed.user.student.application.internal.commandService;

import com.studmed.user.student.domain.model.aggregates.Student;
import com.studmed.user.student.domain.model.commands.CreateStudentCommand;
import com.studmed.user.student.domain.service.StudentCommandService;
import com.studmed.user.student.infraestructure.persistance.jpa.respositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {

    private final StudentRepository studentRepository;

    public StudentCommandServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Long handle(CreateStudentCommand command) {
        if (studentRepository.existsByStudentCode(command.studentCode())) {
            throw new IllegalArgumentException("Ya existe un estudiante con ese código");
        }

        Student student = new Student(command);
        return studentRepository.save(student).getId();
    }
}
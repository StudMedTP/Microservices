package com.studmed.user.student.application.internal.commandService;

import com.studmed.user.student.domain.model.aggregates.Student;
import com.studmed.user.student.domain.model.commands.CreateStudentCommand;
import com.studmed.user.student.domain.service.StudentCommandService;
import com.studmed.user.student.infraestructure.persistance.jpa.respositories.StudentRepository;
import com.studmed.user.user.domain.model.aggregates.User;
import com.studmed.user.user.infraestructure.persistance.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentCommandServiceImpl(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateStudentCommand command) {
        Optional<User> userOptional = userRepository.findById(command.userId());

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Existe un error en los datos ingresados");
        }

        if (studentRepository.existsByStudentCode(command.studentCode())) {
            throw new IllegalArgumentException("Ya existe un estudiante con ese código");
        }

        Student student = new Student(command, userOptional.get());
        return studentRepository.save(student).getId();
    }
}
package com.studmed.user.student.application.internal.queryService;

import com.studmed.user.shared.exception.ResourceNotFoundException;
import com.studmed.user.student.domain.model.aggregates.Student;
import com.studmed.user.student.domain.model.queries.GetAllStudentsByTeacherIdQuery;
import com.studmed.user.student.domain.model.queries.GetStudentByIdQuery;
import com.studmed.user.student.domain.model.queries.GetStudentByUserIdQuery;
import com.studmed.user.student.domain.service.StudentQueryService;
import com.studmed.user.student.infraestructure.persistance.jpa.respositories.StudentRepository;
import com.studmed.user.teacher.infraestructure.persistance.jpa.respositories.TeacherRepository;
import com.studmed.user.user.infraestructure.persistance.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentQueryServiceImpl implements StudentQueryService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    public StudentQueryServiceImpl(StudentRepository studentRepository, UserRepository userRepository,
                                   TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Student handle(GetStudentByIdQuery query) {
        Optional<Student> studentOptional = studentRepository.findById(query.id());

        if (studentOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró estudiante");
        }

        Student student = studentOptional.get();
        userRepository.findById(student.getUser().getId()).ifPresent(student::setUser);
        teacherRepository.findById(student.getTeacher().getId()).ifPresent(student::setTeacher);

        return student;
    }

    @Override
    public Student handle(GetStudentByUserIdQuery query) {
        Optional<Student> studentOptional = studentRepository.findByUser_Id(query.id());

        if (studentOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró estudiante");
        }

        Student student = studentOptional.get();
        userRepository.findById(student.getUser().getId()).ifPresent(student::setUser);
        teacherRepository.findById(student.getTeacher().getId()).ifPresent(student::setTeacher);

        return student;
    }

    @Override
    public List<Student> handle(GetAllStudentsByTeacherIdQuery query) {
        return studentRepository.findByTeacher_User_Id(query.id());
    }
}
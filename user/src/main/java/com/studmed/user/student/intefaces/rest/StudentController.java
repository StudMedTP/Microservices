package com.studmed.user.student.intefaces.rest;

import com.studmed.user.shared.exception.BadRequestException;
import com.studmed.user.shared.exception.ResourceNotFoundException;
import com.studmed.user.student.domain.model.aggregates.Student;
import com.studmed.user.student.domain.model.commands.CreateStudentCommand;
import com.studmed.user.student.domain.model.queries.GetStudentByIdQuery;
import com.studmed.user.student.domain.service.StudentCommandService;
import com.studmed.user.student.domain.service.StudentQueryService;
import com.studmed.user.student.intefaces.rest.resource.CreateStudentResource;
import com.studmed.user.student.intefaces.rest.resource.StudentResource;
import com.studmed.user.student.intefaces.rest.transform.CreateStudentCommandFromResourceAssembler;
import com.studmed.user.student.intefaces.rest.transform.StudentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/students")
@Tag(name = "Student", description = "Student Management Endpoints")
public class StudentController {

    private final StudentCommandService studentCommandService;
    private final StudentQueryService studentQueryService;

    public StudentController(StudentCommandService studentCommandService, StudentQueryService studentQueryService) {
        this.studentCommandService = studentCommandService;
        this.studentQueryService = studentQueryService;
    }

    @PostMapping
    public ResponseEntity<StudentResource> createStudent(@RequestBody @Valid CreateStudentResource createStudentResource) {

        CreateStudentCommand createStudentCommand = CreateStudentCommandFromResourceAssembler.toCommandFromResource(createStudentResource);
        Long id = studentCommandService.handle(createStudentCommand);

        Optional<Student> student = studentQueryService.handle(new GetStudentByIdQuery(id));
        if (student.isEmpty()) {
            throw new IllegalArgumentException("El estudiante no pudo ser creado");
        }

        StudentResource studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(studentResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResource> getStudentById(@PathVariable Long id) {
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        Optional<Student> student = studentQueryService.handle(new GetStudentByIdQuery(id));
        if (student.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró estudiante");
        }

        StudentResource studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());
        return ResponseEntity.ok(studentResource);
    }
}
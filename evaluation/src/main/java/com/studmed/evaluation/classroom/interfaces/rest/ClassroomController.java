package com.studmed.evaluation.classroom.interfaces.rest;

import com.studmed.evaluation.classroom.domain.model.aggregate.Classroom;
import com.studmed.evaluation.classroom.domain.model.commands.CreateClassroomCommand;
import com.studmed.evaluation.classroom.domain.model.queries.GetAllClassroomByUserIdQuery;
import com.studmed.evaluation.classroom.domain.model.queries.GetClassroomByIdQuery;
import com.studmed.evaluation.classroom.domain.service.ClassroomCommandService;
import com.studmed.evaluation.classroom.domain.service.ClassroomQueryService;
import com.studmed.evaluation.classroom.interfaces.rest.resource.ClassroomResource;
import com.studmed.evaluation.classroom.interfaces.rest.resource.ClassroomResourcePlain;
import com.studmed.evaluation.classroom.interfaces.rest.resource.CreateClassroomResource;
import com.studmed.evaluation.classroom.interfaces.rest.transform.ClassroomResourceFromEntityAssembler;
import com.studmed.evaluation.classroom.interfaces.rest.transform.CreateClassroomCommandFromResourceAssembler;
import com.studmed.evaluation.shared.exception.BadRequestException;
import com.studmed.evaluation.shared.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/classrooms")
@Tag(name = "Classroom", description = "Classroom Management Endpoints")
public class ClassroomController {

    private final ClassroomCommandService classroomCommandService;
    private final ClassroomQueryService classroomQueryService;

    public ClassroomController(ClassroomCommandService classroomCommandService, ClassroomQueryService classroomQueryService){
        this.classroomCommandService = classroomCommandService;
        this.classroomQueryService = classroomQueryService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Classroom Microservice is up and running! 1.0");
    }

    @PostMapping
    public ResponseEntity<ClassroomResourcePlain> createClassroom(@RequestBody @Valid CreateClassroomResource createClassroomResource){
        CreateClassroomCommand createClassroomCommand = CreateClassroomCommandFromResourceAssembler.toCommandFromResource(createClassroomResource);
        Long id = classroomCommandService.handle(createClassroomCommand);

        Classroom classroom = classroomQueryService.handle(new GetClassroomByIdQuery(id));

        ClassroomResourcePlain classroomResource = ClassroomResourceFromEntityAssembler.toResourcePlainFromEntity(classroom);
        return ResponseEntity.status(HttpStatus.CREATED).body(classroomResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomResource> getAttendanceById(@PathVariable Long id){
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        Classroom classroom = classroomQueryService.handle(new GetClassroomByIdQuery(id));

        ClassroomResource classroomResource = ClassroomResourceFromEntityAssembler.toResourceFromEntity(classroom);
        return ResponseEntity.ok(classroomResource);
    }

    @GetMapping("/myObject")
    public ResponseEntity<Map<String, List<ClassroomResource>>> getClassroomsByToken(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<Classroom> classrooms = classroomQueryService.handle(new GetAllClassroomByUserIdQuery(userDetails.id()));

        List<ClassroomResource> classroomResources = classrooms.stream().map(ClassroomResourceFromEntityAssembler::toResourceFromEntity).toList();

        Map<String, List<ClassroomResource>> response = Map.of("classrooms", classroomResources);
        return ResponseEntity.ok(response);
    }
}
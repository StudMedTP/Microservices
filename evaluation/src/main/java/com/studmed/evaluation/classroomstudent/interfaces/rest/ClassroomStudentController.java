package com.studmed.evaluation.classroomstudent.interfaces.rest;

import com.studmed.evaluation.classroomstudent.domain.model.aggregate.ClassroomStudent;
import com.studmed.evaluation.classroomstudent.domain.model.commands.CreateClassroomStudentCommand;
import com.studmed.evaluation.classroomstudent.domain.model.queries.GetAllClassroomStudentByClassIdQuery;
import com.studmed.evaluation.classroomstudent.domain.model.queries.GetAllClassroomStudentByUserIdQuery;
import com.studmed.evaluation.classroomstudent.domain.model.queries.GetClassroomStudentByIdQuery;
import com.studmed.evaluation.classroomstudent.domain.service.ClassroomStudentCommandService;
import com.studmed.evaluation.classroomstudent.domain.service.ClassroomStudentQueryService;
import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.ClassroomStudentResource;
import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.ClassroomStudentResourcePlain;
import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.CreateClassroomStudentResource;
import com.studmed.evaluation.classroomstudent.interfaces.rest.transform.ClassroomStudentResourceFromEntityAssembler;
import com.studmed.evaluation.classroomstudent.interfaces.rest.transform.CreateClassroomStudentCommandFromResourceAssembler;
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
@RequestMapping(value = "/classroom-students")
@Tag(name = "ClassroomStudent", description = "ClassroomStudent Management Endpoints")
public class ClassroomStudentController {

    private final ClassroomStudentCommandService classroomStudentCommandService;
    private final ClassroomStudentQueryService classroomQueryService;

    public ClassroomStudentController(ClassroomStudentCommandService classroomStudentCommandService, ClassroomStudentQueryService classroomQueryService){
        this.classroomStudentCommandService = classroomStudentCommandService;
        this.classroomQueryService = classroomQueryService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("ClassroomStudent Microservice is up and running! 1.0");
    }

    @PostMapping
    public ResponseEntity<ClassroomStudentResourcePlain> createClassroomStudent(@RequestBody @Valid CreateClassroomStudentResource createClassroomStudentResource){
        CreateClassroomStudentCommand createClassroomStudentCommand = CreateClassroomStudentCommandFromResourceAssembler.toCommandFromResource(createClassroomStudentResource);
        Long id = classroomStudentCommandService.handle(createClassroomStudentCommand);

        ClassroomStudent classroomStudent = classroomQueryService.handle(new GetClassroomStudentByIdQuery(id));

        ClassroomStudentResourcePlain classroomStudentResource = ClassroomStudentResourceFromEntityAssembler.toResourcePlainFromEntity(classroomStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(classroomStudentResource);
    }

    @GetMapping("/myObject")
    public ResponseEntity<Map<String, List<ClassroomStudentResource>>> getClassroomStudentsByToken(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<ClassroomStudent> classrooms = classroomQueryService.handle(new GetAllClassroomStudentByUserIdQuery(userDetails.id()));

        List<ClassroomStudentResource> classroomStudentResources = classrooms.stream().map(ClassroomStudentResourceFromEntityAssembler::toResourceFromEntity).toList();

        Map<String, List<ClassroomStudentResource>> response = Map.of("classrooms-students", classroomStudentResources);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/classroom/{id}")
    public ResponseEntity<Map<String, List<ClassroomStudentResource>>> getClassroomStudentsByClassroomId(@PathVariable Long id) {
        List<ClassroomStudent> classrooms = classroomQueryService.handle(new GetAllClassroomStudentByClassIdQuery(id));

        List<ClassroomStudentResource> classroomStudentResources = classrooms.stream().map(ClassroomStudentResourceFromEntityAssembler::toResourceFromEntity).toList();

        Map<String, List<ClassroomStudentResource>> response = Map.of("classrooms-students", classroomStudentResources);
        return ResponseEntity.ok(response);
    }
}
package com.studmed.evaluation.grade.interfaces.rest;

import com.studmed.evaluation.grade.domain.model.aggregate.Grade;
import com.studmed.evaluation.grade.domain.model.commands.CreateGradeCommand;
import com.studmed.evaluation.grade.domain.model.queries.GetAllGradeByClassStudentIdQuery;
import com.studmed.evaluation.grade.domain.model.queries.GetGradeByIdQuery;
import com.studmed.evaluation.grade.domain.service.GradeCommandService;
import com.studmed.evaluation.grade.domain.service.GradeQueryService;
import com.studmed.evaluation.grade.interfaces.rest.resource.CreateGradeResource;
import com.studmed.evaluation.grade.interfaces.rest.resource.GradeResource;
import com.studmed.evaluation.grade.interfaces.rest.resource.GradeResourcePlain;
import com.studmed.evaluation.grade.interfaces.rest.transform.CreateGradeCommandFromResourceAssembler;
import com.studmed.evaluation.grade.interfaces.rest.transform.GradeResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/grades")
@Tag(name = "Grade", description = "Grade Management Endpoints")
public class GradeController {

    private final GradeCommandService gradeCommandService;
    private final GradeQueryService gradeQueryService;

    public GradeController(GradeCommandService gradeCommandService, GradeQueryService gradeQueryService){
        this.gradeCommandService = gradeCommandService;
        this.gradeQueryService = gradeQueryService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Grade Microservice is up and running! 1.0");
    }

    @PostMapping
    public ResponseEntity<GradeResourcePlain> createGrade(@RequestBody @Valid CreateGradeResource createGradeResource){
        CreateGradeCommand createGradeCommand = CreateGradeCommandFromResourceAssembler.toCommandFromResource(createGradeResource);
        Long id = gradeCommandService.handle(createGradeCommand);

        Grade grade = gradeQueryService.handle(new GetGradeByIdQuery(id));

        GradeResourcePlain gradeResource = GradeResourceFromEntityAssembler.toResourcePlainFromEntity(grade);
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeResource);
    }

    @GetMapping("/classroom-student/{id}")
    public ResponseEntity<Map<String, List<GradeResource>>> getGradesByClassroomStudentId(@PathVariable Long id) {
        List<Grade> grades = gradeQueryService.handle(new GetAllGradeByClassStudentIdQuery(id));

        List<GradeResource> gradeResources = grades.stream().map(GradeResourceFromEntityAssembler::toResourceFromEntity).toList();

        Map<String, List<GradeResource>> response = Map.of("grades", gradeResources);
        return ResponseEntity.ok(response);
    }
}
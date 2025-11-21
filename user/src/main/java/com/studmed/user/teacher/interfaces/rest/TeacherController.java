package com.studmed.user.teacher.interfaces.rest;

import com.studmed.user.teacher.domain.model.aggregates.Teacher;
import com.studmed.user.teacher.domain.model.commands.CloseClassByIdCommand;
import com.studmed.user.teacher.domain.model.commands.CreateTeacherCommand;
import com.studmed.user.teacher.domain.model.commands.OpenClassByIdCommand;
import com.studmed.user.teacher.domain.model.queries.GetTeacherByIdAndDailyCodeQuery;
import com.studmed.user.teacher.domain.model.queries.GetTeacherByIdQuery;
import com.studmed.user.teacher.domain.service.TeacherCommandService;
import com.studmed.user.teacher.domain.service.TeacherQueryService;
import com.studmed.user.teacher.interfaces.rest.resource.TeacherResource;
import com.studmed.user.teacher.interfaces.rest.resource.CreateTeacherResource;
import com.studmed.user.teacher.interfaces.rest.transform.CloseClassByIdCommandFromResourceAssembler;
import com.studmed.user.teacher.interfaces.rest.transform.OpenClassByIdCommandFromResourceAssembler;
import com.studmed.user.teacher.interfaces.rest.transform.TeacherResourceFromEntityAssembler;
import com.studmed.user.teacher.interfaces.rest.transform.CreateTeacherCommandFromResourceAssembler;
import com.studmed.user.shared.exception.BadRequestException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/teachers")
@Tag(name = "Teacher", description = "Teacher Management Endpoints")
public class TeacherController {

    private final TeacherCommandService teacherCommandService;
    private final TeacherQueryService teacherQueryService;

    public TeacherController(TeacherCommandService teacherCommandService, TeacherQueryService teacherQueryService) {
        this.teacherCommandService = teacherCommandService;
        this.teacherQueryService = teacherQueryService;
    }

    @PostMapping
    public ResponseEntity<TeacherResource> createTeacher(@RequestBody @Valid CreateTeacherResource createTeacherResource) {
        CreateTeacherCommand createTeacherCommand = CreateTeacherCommandFromResourceAssembler.toCommandFromResource(createTeacherResource);
        Long id = teacherCommandService.handle(createTeacherCommand);

        Teacher teacher = teacherQueryService.handle(new GetTeacherByIdQuery(id));

        TeacherResource teacherResource = TeacherResourceFromEntityAssembler.toResourceFromEntity(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResource> getTeacherById(@PathVariable Long id) {
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        Teacher teacher = teacherQueryService.handle(new GetTeacherByIdQuery(id));

        TeacherResource teacherResource = TeacherResourceFromEntityAssembler.toResourceFromEntity(teacher);
        return ResponseEntity.ok(teacherResource);
    }

    @GetMapping("/openClass/{id}")
    public ResponseEntity<TeacherResource> openClassById(@PathVariable Long id) {
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        OpenClassByIdCommand openClassByIdCommand = OpenClassByIdCommandFromResourceAssembler.toCommandFromResource(id);
        Long teacherId = teacherCommandService.handle(openClassByIdCommand);

        Teacher teacher = teacherQueryService.handle(new GetTeacherByIdQuery(teacherId));

        TeacherResource teacherResource = TeacherResourceFromEntityAssembler.toResourceFromEntity(teacher);
        return ResponseEntity.ok(teacherResource);
    }

    @GetMapping("/closeClass/{id}")
    public ResponseEntity<TeacherResource> closeClassById(@PathVariable Long id) {
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        CloseClassByIdCommand closeClassByIdCommand = CloseClassByIdCommandFromResourceAssembler.toCommandFromResource(id);
        Long teacherId = teacherCommandService.handle(closeClassByIdCommand);

        Teacher teacher = teacherQueryService.handle(new GetTeacherByIdQuery(teacherId));

        TeacherResource teacherResource = TeacherResourceFromEntityAssembler.toResourceFromEntity(teacher);
        return ResponseEntity.ok(teacherResource);
    }

    @GetMapping("/verifyDailyCode/{teacherId}/{dailyCode}")
    public ResponseEntity<TeacherResource> verifyDailyCode(@PathVariable Long teacherId, @PathVariable String dailyCode) {
        if (teacherId <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        Teacher teacher = teacherQueryService.handle(new GetTeacherByIdAndDailyCodeQuery(teacherId, dailyCode));

        TeacherResource teacherResource = TeacherResourceFromEntityAssembler.toResourceFromEntity(teacher);
        return ResponseEntity.ok(teacherResource);
    }
}
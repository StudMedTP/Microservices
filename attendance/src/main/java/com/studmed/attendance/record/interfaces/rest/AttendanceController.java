package com.studmed.attendance.record.interfaces.rest;

import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.domain.model.commands.CreateAttendanceCommand;
import com.studmed.attendance.record.domain.model.commands.DeleteAttendanceCommand;
import com.studmed.attendance.record.domain.model.commands.UpdateAttendanceCommand;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceByUserIdQuery;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceQuery;
import com.studmed.attendance.record.domain.model.queries.GetAttendanceByIdQuery;
import com.studmed.attendance.record.domain.service.AttendanceCommandService;
import com.studmed.attendance.record.domain.service.AttendanceQueryService;
import com.studmed.attendance.record.interfaces.rest.resource.AttendanceResource;
import com.studmed.attendance.record.interfaces.rest.resource.UpdateAttendanceResource;
import com.studmed.attendance.record.interfaces.rest.transform.CreateAttendanceCommandFromResourceAssembler;
import com.studmed.attendance.record.interfaces.rest.transform.AttendanceResourceFromEntityAssembler;
import com.studmed.attendance.record.interfaces.rest.transform.UpdateAttendanceCommandFromResourceAssembler;
import com.studmed.attendance.record.interfaces.rest.resource.CreateAttendanceResource;
import com.studmed.attendance.shared.exception.BadRequestException;
import com.studmed.attendance.shared.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/attendances")
@Tag(name = "Attendance", description = "Attendance Management Endpoints")
public class AttendanceController {

    private final AttendanceCommandService attendanceCommandService;
    private final AttendanceQueryService attendanceQueryService;

    public AttendanceController(AttendanceCommandService attendanceCommandService, AttendanceQueryService attendanceQueryService){
        this.attendanceCommandService = attendanceCommandService;
        this.attendanceQueryService = attendanceQueryService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Attendance Microservice is up and running! 1.5");
    }

    @PostMapping
    public ResponseEntity<AttendanceResource> createAttendance(@RequestBody @Valid CreateAttendanceResource createAttendanceResource){
        CreateAttendanceCommand createAttendanceCommand = CreateAttendanceCommandFromResourceAssembler.toCommandFromResource(createAttendanceResource);
        Long id = attendanceCommandService.handle(createAttendanceCommand);

        Attendance attendance = attendanceQueryService.handle(new GetAttendanceByIdQuery(id));

        AttendanceResource attendanceResource = AttendanceResourceFromEntityAssembler.toResourceFromEntity(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceResource);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceResource>> getAllAttendances(){
        List<Attendance> attendances = attendanceQueryService.handle(new GetAllAttendanceQuery());

        List<AttendanceResource> attendanceResources = attendances.stream().map(AttendanceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(attendanceResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResource> getAttendanceById(@PathVariable Long id){
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        Attendance attendance = attendanceQueryService.handle(new GetAttendanceByIdQuery(id));

        AttendanceResource attendanceResource = AttendanceResourceFromEntityAssembler.toResourceFromEntity(attendance);
        return ResponseEntity.ok(attendanceResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResource> updateAttendance(@PathVariable Long id, @RequestBody @Valid UpdateAttendanceResource updateAttendanceResource){
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        UpdateAttendanceCommand updateAttendanceCommand = UpdateAttendanceCommandFromResourceAssembler.toCommandFromResource(id, updateAttendanceResource);
        Long attendanceId = attendanceCommandService.handle(updateAttendanceCommand);

        Attendance attendance = attendanceQueryService.handle(new GetAttendanceByIdQuery(attendanceId));

        AttendanceResource attendanceResource = AttendanceResourceFromEntityAssembler.toResourceFromEntity(attendance);
        return ResponseEntity.ok(attendanceResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id){
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        DeleteAttendanceCommand deleteAttendanceCommand = new DeleteAttendanceCommand(id);
        attendanceCommandService.handle(deleteAttendanceCommand);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/myObject")
    public ResponseEntity<Map<String, List<AttendanceResource>>> getAttendancesByToken(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<Attendance> attendances = attendanceQueryService.handle(new GetAllAttendanceByUserIdQuery(userDetails.id()));

        List<AttendanceResource> attendanceResources = attendances.stream().map(AttendanceResourceFromEntityAssembler::toResourceFromEntity).toList();

        Map<String, List<AttendanceResource>> response = Map.of("attendances", attendanceResources);
        return ResponseEntity.ok(response);
    }
}
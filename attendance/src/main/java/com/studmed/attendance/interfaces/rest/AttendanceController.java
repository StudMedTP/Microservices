package com.studmed.attendance.interfaces.rest;

import com.studmed.attendance.domain.model.commands.DeleteAttendanceCommand;
import com.studmed.attendance.domain.model.queries.GetAllAttendanceQuery;
import com.studmed.attendance.domain.model.queries.GetAttendanceByIdQuery;
import com.studmed.attendance.domain.service.AttendanceCommandService;
import com.studmed.attendance.domain.service.AttendanceQueryService;
import com.studmed.attendance.interfaces.rest.resource.AttendanceResource;
import com.studmed.attendance.interfaces.rest.resource.UpdateAttendanceResource;
import com.studmed.attendance.interfaces.rest.transform.CreateAttendanceCommandFromResourceAssembler;
import com.studmed.attendance.interfaces.rest.transform.AttendanceResourceFromEntityAssembler;
import com.studmed.attendance.interfaces.rest.transform.UpdateAttendanceCommandFromResourceAssembler;
import com.studmed.attendance.interfaces.rest.resource.CreateAttendanceResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/attendances", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Attendance", description = "Attendance Management Endpoints")
public class AttendanceController {

    private final AttendanceCommandService attendanceCommandService;
    private final AttendanceQueryService attendanceQueryService;

    public AttendanceController(AttendanceCommandService attendanceCommandService, AttendanceQueryService attendanceQueryService){
        this.attendanceCommandService = attendanceCommandService;
        this.attendanceQueryService = attendanceQueryService;
    }

    @PostMapping
    public ResponseEntity<AttendanceResource> createAttendance(@RequestBody CreateAttendanceResource createAttendanceResource){

        var createAttendanceCommand = CreateAttendanceCommandFromResourceAssembler.toCommandFromResource(createAttendanceResource);
        var id = attendanceCommandService.handle(createAttendanceCommand);
        if (id == 0L){
            return ResponseEntity.badRequest().build();
        }

        var getAttendancesByIdQuery = new GetAttendanceByIdQuery(id);
        var attendance = attendanceQueryService.handle(getAttendancesByIdQuery);
        if (attendance.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var attendanceResource = AttendanceResourceFromEntityAssembler.toResourceFromEntity(attendance.get());
        return new ResponseEntity<>(attendanceResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceResource>> getAllAttendances(){
        var getAllAttendanceQuery = new GetAllAttendanceQuery();
        var attendance = attendanceQueryService.handle(getAllAttendanceQuery);
        var attendanceResource = attendance.stream().map(AttendanceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(attendanceResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResource> getAttendanceById(@PathVariable Long id){
        var getAttendanceByIdQuery = new GetAttendanceByIdQuery(id);
        var attendance = attendanceQueryService.handle(getAttendanceByIdQuery);
        if (attendance.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var attendanceResource = AttendanceResourceFromEntityAssembler.toResourceFromEntity(attendance.get());
        return ResponseEntity.ok(attendanceResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResource> updateAttendance(@PathVariable Long id, @RequestBody UpdateAttendanceResource updateAttendanceResource){
        var updateAttendanceCommand = UpdateAttendanceCommandFromResourceAssembler.toCommandFromResource(id, updateAttendanceResource);
        var updateAttendance = attendanceCommandService.handle(updateAttendanceCommand);
        if (updateAttendance.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var attendanceResource = AttendanceResourceFromEntityAssembler.toResourceFromEntity(updateAttendance.get());
        return ResponseEntity.ok(attendanceResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long id){
        var deleteAttendanceCommand = new DeleteAttendanceCommand(id);
        attendanceCommandService.handle(deleteAttendanceCommand);
        return ResponseEntity.ok("Attendance with given id successfully deleted");
    }
}
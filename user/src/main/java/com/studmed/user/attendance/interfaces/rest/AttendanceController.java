package com.studmed.user.attendance.interfaces.rest;

import com.studmed.user.attendance.domain.model.Attendance;
import com.studmed.user.attendance.domain.service.AttendanceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.List;

@RestController
@RequestMapping(value = "/attendance")
@Tag(name = "Attendance", description = "Attendance Management Endpoints")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/{professorId}/{studentId}/{latitude}/{longitude}")
    public ResponseEntity<TransactionReceipt> recordAttendance(@PathVariable Long professorId, @PathVariable Long studentId, @PathVariable Long latitude, @PathVariable Long longitude) {
        TransactionReceipt receipt = attendanceService.recordAttendance(professorId, studentId, latitude, longitude);
        return ResponseEntity.status(HttpStatus.CREATED).body(receipt);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getByStudent(@PathVariable Long studentId) {
        List<Attendance> attendances = attendanceService.getByStudent(studentId);
        return ResponseEntity.ok(attendances);
    }
}
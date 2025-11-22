package com.studmed.attendance.attendance.interfaces.rest;

import com.studmed.attendance.attendance.domain.model.BlockchainAttendance;
import com.studmed.attendance.attendance.domain.service.BlockchainService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.List;

@RestController
@RequestMapping(value = "/blockchains")
@Tag(name = "Blockchain", description = "Blockchain Management Endpoints")
public class BlockchainController {

    private final BlockchainService blockchainService;

    public BlockchainController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    @PostMapping("/{professorId}/{studentId}/{latitude}/{longitude}")
    public ResponseEntity<TransactionReceipt> recordAttendance(@PathVariable Long professorId, @PathVariable Long studentId, @PathVariable Long latitude, @PathVariable Long longitude) {
        TransactionReceipt receipt = blockchainService.recordAttendance(professorId, studentId, latitude, longitude);
        return ResponseEntity.status(HttpStatus.CREATED).body(receipt);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<BlockchainAttendance>> getByStudent(@PathVariable Long studentId) {
        List<BlockchainAttendance> attendances = blockchainService.getByStudent(studentId);
        return ResponseEntity.ok(attendances);
    }
}
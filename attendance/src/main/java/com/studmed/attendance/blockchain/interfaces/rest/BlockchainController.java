package com.studmed.attendance.blockchain.interfaces.rest;

import com.studmed.attendance.blockchain.domain.model.BlockchainAttendance;
import com.studmed.attendance.blockchain.domain.service.BlockchainService;
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
    public ResponseEntity<TransactionReceipt> recordAttendance(@PathVariable Long professorId, @PathVariable Long studentId, @PathVariable Double latitude, @PathVariable Double longitude) {
        TransactionReceipt receipt = blockchainService.recordAttendance(professorId, studentId, latitude, longitude);
        return ResponseEntity.status(HttpStatus.CREATED).body(receipt);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<BlockchainAttendance>> getByStudent(@PathVariable Long studentId) {
        List<BlockchainAttendance> attendances = blockchainService.getByStudent(studentId);
        return ResponseEntity.ok(attendances);
    }
}
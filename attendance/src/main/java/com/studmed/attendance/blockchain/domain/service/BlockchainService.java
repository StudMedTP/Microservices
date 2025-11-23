package com.studmed.attendance.blockchain.domain.service;

import com.studmed.attendance.blockchain.domain.model.BlockchainAttendance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.List;

public interface BlockchainService {
    TransactionReceipt recordAttendance(long attendanceId, long professorId, long studentId, Double latitude, Double longitude);
    List<BlockchainAttendance> getByStudent(long studentId);
    List<BlockchainAttendance> getByAttendance(long attendanceId);
}
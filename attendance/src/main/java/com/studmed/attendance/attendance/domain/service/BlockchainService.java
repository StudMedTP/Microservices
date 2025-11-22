package com.studmed.attendance.attendance.domain.service;

import com.studmed.attendance.attendance.domain.model.BlockchainAttendance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.List;

public interface BlockchainService {
    TransactionReceipt recordAttendance(long professorId, long studentId, long latitude, long longitude);
    List<BlockchainAttendance> getByStudent(long studentId);
}
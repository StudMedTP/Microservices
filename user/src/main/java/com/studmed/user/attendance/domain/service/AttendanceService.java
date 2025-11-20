package com.studmed.user.attendance.domain.service;

import com.studmed.user.attendance.domain.model.Attendance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.List;

public interface AttendanceService {
    TransactionReceipt recordAttendance(long professorId, long studentId, long latitude, long longitude);
    List<Attendance> getByStudent(long studentId);
}
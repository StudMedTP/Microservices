package com.studmed.attendance.application.internal.queryservice;

import com.studmed.attendance.domain.model.aggregates.Attendance;
import com.studmed.attendance.domain.model.queries.GetAllAttendanceQuery;
import com.studmed.attendance.domain.model.queries.GetAttendanceByIdQuery;
import com.studmed.attendance.domain.service.AttendanceQueryService;
import com.studmed.attendance.infraestructure.persistance.jpa.repositories.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceQueryServiceImpl implements AttendanceQueryService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceQueryServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Optional<Attendance> handle (GetAttendanceByIdQuery query){
        return attendanceRepository.findById(query.id());
    }

    @Override
    public List<Attendance> handle (GetAllAttendanceQuery query){
        return attendanceRepository.findAll();
    }
}
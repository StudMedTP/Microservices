package com.studmed.attendance.record.application.internal.queryservice;

import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceByUserIdQuery;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceQuery;
import com.studmed.attendance.record.domain.model.queries.GetAttendanceByIdQuery;
import com.studmed.attendance.record.domain.service.AttendanceQueryService;
import com.studmed.attendance.record.infraestructure.persistance.jpa.repositories.AttendanceRepository;
import com.studmed.attendance.shared.exception.ResourceNotFoundException;
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
    public Attendance handle (GetAttendanceByIdQuery query){
        Optional<Attendance> attendanceOptional = attendanceRepository.findById(query.id());

        if (attendanceOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró usuario");
        }

        return attendanceOptional.get();
    }

    @Override
    public List<Attendance> handle (GetAllAttendanceQuery query){
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> handle (GetAllAttendanceByUserIdQuery query){
        //TODO add client feign connection to validate if student with userid exists
        return attendanceRepository.findAllByStudentId(query.userId());
    }
}
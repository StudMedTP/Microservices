package com.studmed.attendance.record.application.internal.queryservice;

import com.studmed.attendance.record.client.UserClient;
import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.domain.model.client.StudentResource;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceByUserIdQuery;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceQuery;
import com.studmed.attendance.record.domain.model.queries.GetAttendanceByIdQuery;
import com.studmed.attendance.record.domain.service.AttendanceQueryService;
import com.studmed.attendance.record.infraestructure.persistance.jpa.repositories.AttendanceRepository;
import com.studmed.attendance.shared.exception.ResourceNotFoundException;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceQueryServiceImpl implements AttendanceQueryService {

    private final AttendanceRepository attendanceRepository;
    public final UserClient userClient;

    public AttendanceQueryServiceImpl(AttendanceRepository attendanceRepository, UserClient userClient) {
        this.attendanceRepository = attendanceRepository;
        this.userClient = userClient;
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
        try {
            StudentResource studentResource = userClient.getStudentByUserId(query.userId()).getBody();

            if (studentResource != null) {
                return attendanceRepository.findAllByStatusAndStudentId("PENDIENTE", studentResource.getId());
            } else {
                throw new RuntimeException("Error al validar estudiante.");
            }
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El estudiante no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante.");
        }
    }
}
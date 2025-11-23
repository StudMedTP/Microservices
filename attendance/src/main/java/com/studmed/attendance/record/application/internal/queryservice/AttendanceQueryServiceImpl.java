package com.studmed.attendance.record.application.internal.queryservice;

import com.studmed.attendance.record.client.UserClient;
import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.domain.model.client.MedicalCenterResource;
import com.studmed.attendance.record.domain.model.client.StudentResource;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceByUserIdQuery;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceQuery;
import com.studmed.attendance.record.domain.model.queries.GetAttendanceByIdQuery;
import com.studmed.attendance.record.domain.model.queries.GetLastAttendanceByStudentIdQuery;
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
                List<Attendance> attendances = attendanceRepository.findAllByStatusAndStudentId("PENDIENTE", studentResource.getId());

                attendances.forEach((attendance) -> {
                    try {
                        StudentResource studentResourceAttendance = userClient.getStudentById(attendance.getStudentId()).getBody();
                        attendance.setStudent(studentResourceAttendance);
                    } catch (Exception e) {
                        StudentResource studentResourceAttendance = StudentResource.builder().build();
                        attendance.setStudent(studentResourceAttendance);
                    }

                    try {
                        MedicalCenterResource medicalCenterResourceAttendance = userClient.getMedicalCenterById(attendance.getMedicalCenterId()).getBody();
                        attendance.setMedicalCenter(medicalCenterResourceAttendance);
                    } catch (Exception e) {
                        MedicalCenterResource medicalCenterResourceAttendance = MedicalCenterResource.builder().build();
                        attendance.setMedicalCenter(medicalCenterResourceAttendance);
                    }
                });
                return attendances;
            } else {
                throw new RuntimeException("Error al validar estudiante.");
            }
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("El estudiante no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante.");
        }
    }

    @Override
    public Attendance handle (GetLastAttendanceByStudentIdQuery query){
        try {
            userClient.getStudentById(query.studentId());

            Optional<Attendance> attendanceOptional = attendanceRepository.findTopByStudentIdOrderByCreatedAtDesc(query.studentId());

            if (attendanceOptional.isEmpty()) {
                throw new ResourceNotFoundException("No se encontró asistencia");
            }

            Attendance attendance = attendanceOptional.get();

            try {
                StudentResource studentResourceAttendance = userClient.getStudentById(attendance.getStudentId()).getBody();
                attendance.setStudent(studentResourceAttendance);
            } catch (Exception e) {
                StudentResource studentResourceAttendance = StudentResource.builder().build();
                attendance.setStudent(studentResourceAttendance);
            }

            try {
                MedicalCenterResource medicalCenterResourceAttendance = userClient.getMedicalCenterById(attendance.getMedicalCenterId()).getBody();
                attendance.setMedicalCenter(medicalCenterResourceAttendance);
            } catch (Exception e) {
                MedicalCenterResource medicalCenterResourceAttendance = MedicalCenterResource.builder().build();
                attendance.setMedicalCenter(medicalCenterResourceAttendance);
            }

            return attendance;
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El estudiante no existe.");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No se encontró asistencia");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante.");
        }
    }
}
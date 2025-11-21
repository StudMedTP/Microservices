package com.studmed.attendance.record.application.internal.commandservice;

import com.studmed.attendance.record.client.UserClient;
import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.domain.model.commands.CreateAttendanceCommand;
import com.studmed.attendance.record.domain.model.commands.DeleteAttendanceCommand;
import com.studmed.attendance.record.domain.model.commands.UpdateAttendanceCommand;
import com.studmed.attendance.record.domain.service.AttendanceCommandService;
import com.studmed.attendance.record.infraestructure.persistance.jpa.repositories.AttendanceRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendanceCommandServiceImpl implements AttendanceCommandService {

    private final AttendanceRepository attendanceRepository;
    public final UserClient userClient;

    public AttendanceCommandServiceImpl(AttendanceRepository attendanceRepository, UserClient userClient) {
        this.attendanceRepository = attendanceRepository;
        this.userClient = userClient;
    }

    @Override
    public Long handle(CreateAttendanceCommand command) {
        try {
            userClient.getStudentById(command.studentId());
            userClient.getMedicalCenterById(command.medicalCenterId());

            Attendance attendance =  new Attendance(command);
            return attendanceRepository.save(attendance).getId();
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El estudiante o centro médico no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante o centro médico.");
        }
    }

    @Override
    public Long handle (UpdateAttendanceCommand command) {
        Optional<Attendance> attendanceOptional = attendanceRepository.findById(command.id());

        if (attendanceOptional.isEmpty()){
            throw new IllegalArgumentException("No se encontró asistencia");
        }

        Attendance attendance = attendanceOptional.get();

        attendance.setStatus(command.status());

        return attendanceRepository.save(attendance).getId();
    }

    @Override
    public void handle (DeleteAttendanceCommand command) {
        if(!attendanceRepository.existsById(command.id())) {
            throw new IllegalArgumentException("No se encontró asistencia");
        }

        attendanceRepository.deleteById(command.id());
    }
}
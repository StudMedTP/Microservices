package com.studmed.attendance.record.application.internal.commandservice;

import com.studmed.attendance.blockchain.domain.service.BlockchainService;
import com.studmed.attendance.record.client.EvaluationClient;
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
    private final BlockchainService blockchainService;
    public final UserClient userClient;
    public final EvaluationClient evaluationClient;

    public AttendanceCommandServiceImpl(AttendanceRepository attendanceRepository, BlockchainService blockchainService,
                                        UserClient userClient, EvaluationClient evaluationClient) {
        this.attendanceRepository = attendanceRepository;
        this.blockchainService = blockchainService;
        this.userClient = userClient;
        this.evaluationClient = evaluationClient;
    }

    @Override
    public Long handle(CreateAttendanceCommand command) {
        try {
            userClient.getStudentById(command.studentId());
            userClient.getTeacherById(command.teacherId());

            evaluationClient.getAttendanceById(command.classroomId());

            Attendance attendance =  new Attendance(command);

            Long attendanceId = attendanceRepository.save(attendance).getId();

            blockchainService.recordAttendance(attendanceId, command.teacherId(), command.studentId(), command.latitude(), command.longitude());

            return attendanceId;
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El estudiante, la clase o el profesor no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante, clase o profesor.");
        }
    }

    @Override
    public Long handle (UpdateAttendanceCommand command) {
        Optional<Attendance> attendanceOptional = attendanceRepository.findById(command.id());

        if (attendanceOptional.isEmpty()){
            throw new IllegalArgumentException("No se encontró asistencia");
        }

        Attendance attendance = attendanceOptional.get();

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
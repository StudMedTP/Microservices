package com.studmed.attendance.record.application.internal.commandservice;

import com.studmed.attendance.blockchain.domain.service.BlockchainService;
import com.studmed.attendance.record.client.EvaluationClient;
import com.studmed.attendance.record.client.UserClient;
import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.domain.model.client.MedicalCenterResource;
import com.studmed.attendance.record.domain.model.client.TeacherResource;
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

    double HOSPITAL_ALLOWED_RADIUS_METERS = 100.0;

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
            TeacherResource teacherResource = userClient.getTeacherById(command.teacherId()).getBody();

            evaluationClient.getAttendanceById(command.classroomId());

            validateAttendanceLocation(command, teacherResource);

            Attendance attendance =  new Attendance(command);

            Long attendanceId = attendanceRepository.save(attendance).getId();

            //blockchainService.recordAttendance(attendanceId, command.teacherId(), command.studentId(), command.latitude(), command.longitude());

            return attendanceId;
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El estudiante, la clase o el profesor no existe.");
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante, clase o profesor.");
        }
    }

    private void validateAttendanceLocation(CreateAttendanceCommand command, TeacherResource teacherResource) {
        if (teacherResource == null || teacherResource.getMedicalCenterResource() == null) {
            throw new IllegalArgumentException("El profesor no tiene un centro médico asignado.");
        }

        MedicalCenterResource medicalCenter = teacherResource.getMedicalCenterResource();

        double distanceInMeters = calculateDistanceInMeters(command.latitude(), command.longitude(), medicalCenter.getLatitude(), medicalCenter.getLongitude());

        if (distanceInMeters > HOSPITAL_ALLOWED_RADIUS_METERS) {
            throw new IllegalArgumentException("La ubicación está fuera del radio permitido del hospital.");
        }
    }

    private double calculateDistanceInMeters(
            double latitude1,
            double longitude1,
            double latitude2,
            double longitude2
    ) {
        final int earthRadiusInMeters = 6371000;

        double latDistance = Math.toRadians(latitude2 - latitude1);
        double lonDistance = Math.toRadians(longitude2 - longitude1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude1))
                * Math.cos(Math.toRadians(latitude2))
                * Math.sin(lonDistance / 2)
                * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadiusInMeters * c;
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
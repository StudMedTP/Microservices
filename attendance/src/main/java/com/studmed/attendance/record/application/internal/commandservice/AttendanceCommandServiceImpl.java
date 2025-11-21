package com.studmed.attendance.record.application.internal.commandservice;

import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.domain.model.commands.CreateAttendanceCommand;
import com.studmed.attendance.record.domain.model.commands.DeleteAttendanceCommand;
import com.studmed.attendance.record.domain.model.commands.UpdateAttendanceCommand;
import com.studmed.attendance.record.domain.service.AttendanceCommandService;
import com.studmed.attendance.record.infraestructure.persistance.jpa.repositories.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendanceCommandServiceImpl implements AttendanceCommandService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceCommandServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Long handle(CreateAttendanceCommand command) {
        //TODO add client feign connection to validate if student and medical center exists
        Attendance attendance =  new Attendance(command);

        return attendanceRepository.save(attendance).getId();
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
package com.studmed.attendance.application.internal.commandservice;

import com.studmed.attendance.domain.model.aggregates.Attendance;
import com.studmed.attendance.domain.model.commands.CreateAttendanceCommand;
import com.studmed.attendance.domain.model.commands.DeleteAttendanceCommand;
import com.studmed.attendance.domain.model.commands.UpdateAttendanceCommand;
import com.studmed.attendance.domain.service.AttendanceCommandService;
import com.studmed.attendance.infraestructure.persistance.jpa.repositories.AttendanceRepository;
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
        if (attendanceRepository.existsByVerificationToken(command.verificationToken())){
            throw new IllegalArgumentException("Attendance Already Exists");
        }
        Attendance attendance =  new Attendance(command);
        try {
            attendanceRepository.save(attendance);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving attendance" + e.getMessage());
        }
        return attendance.getId();
    }

    @Override
    public Optional<Attendance> handle (UpdateAttendanceCommand command) {

        if (attendanceRepository.existsByVerificationTokenAndIdIsNot(command.verificationToken(), command.id())){
            throw new IllegalArgumentException("Attendance with same verification token already exist");
        }

        var result = attendanceRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Attendance does not exist");
        }

        var attendanceToUpdate = result.get();
        try {
            var updatedAttendance = attendanceRepository.save(attendanceToUpdate.updateAttendance(
                    command.attendaceDate(),
                    command.registrationTime(),
                    command.courseName(),
                    command.attendaceState(),
                    command.verificationToken(),
                    command.coordinates()));
            return Optional.of(updatedAttendance);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving attendance" + e.getMessage());
        }
    }

    @Override
    public void handle (DeleteAttendanceCommand command) {

        if (!attendanceRepository.existsById(command.id())){
            throw new IllegalArgumentException("Attendance does not exist");
        }
        try {
            attendanceRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting order" + e.getMessage());
        }
    }
}
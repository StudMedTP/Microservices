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
        if (attendanceRepository.existsByOrderNumber(command.orderNumber())){
            throw new IllegalArgumentException("Attendance Already Exists");
        }
        Attendance Attendance = new Attendance();
        try {
            attendanceRepository.save(Attendance);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving attendance" + e.getMessage());
        }
        return Attendance.getId();
    }

    @Override
    public Optional<Attendance> handle (UpdateAttendanceCommand command) {

        if (attendanceRepository.existsByOrderNumberAndIdIsNot(command.orderNumber(), command.id())){
            throw new IllegalArgumentException("Attendance with same number already exist");
        }

        var result = attendanceRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Attendance does not exist");
        }

        var attendanceToUpdate = result.get();
        try {
            var updatedAttendance = attendanceRepository.save(attendanceToUpdate.updateAttendance(
                    command.orderNumber(),
                    command.orderDate(),
                    command.waitingTime(),
                    command.totalPrice(),
                    command.orderStatus(),
                    command.paymentMethod(),
                    command.paymentAmount()));
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
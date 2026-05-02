package com.studmed.attendance.record.application.internal.queryservice;

import com.studmed.attendance.record.client.EvaluationClient;
import com.studmed.attendance.record.client.UserClient;
import com.studmed.attendance.record.domain.model.aggregates.Attendance;
import com.studmed.attendance.record.domain.model.client.TeacherResource;
import com.studmed.attendance.record.domain.model.client.StudentResource;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceByStudentIdAndClassroomIdQuery;
import com.studmed.attendance.record.domain.model.queries.GetAllAttendanceByUserIdAndClassroomIdQuery;
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
    public final EvaluationClient evaluationClient;

    public AttendanceQueryServiceImpl(AttendanceRepository attendanceRepository, UserClient userClient,
                                      EvaluationClient evaluationClient) {
        this.attendanceRepository = attendanceRepository;
        this.userClient = userClient;
        this.evaluationClient = evaluationClient;
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
    public List<Attendance> handle (GetAllAttendanceByStudentIdAndClassroomIdQuery query) {
        try {
            StudentResource studentResource = userClient.getStudentById(query.studentId()).getBody();

            evaluationClient.getAttendanceById(query.classroomId());

            if (studentResource != null) {
                List<Attendance> attendances = attendanceRepository.findAllByStudentIdAndClassroomId(studentResource.getId(), query.classroomId());

                attendances.forEach((attendance) -> {
                    try {
                        TeacherResource teacherResourceAttendance = userClient.getTeacherById(attendance.getTeacherId()).getBody();
                        attendance.setTeacher(teacherResourceAttendance);
                        attendance.setStudent(studentResource);
                    } catch (Exception e) {
                        TeacherResource teacherResourceAttendance = TeacherResource.builder().build();
                        attendance.setTeacher(teacherResourceAttendance);
                        attendance.setStudent(studentResource);
                    }
                });
                return attendances;
            } else {
                throw new RuntimeException("Error al validar estudiante.");
            }
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("El estudiante o la clase no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante o la clase.");
        }
    }

    @Override
    public List<Attendance> handle (GetAllAttendanceByUserIdAndClassroomIdQuery query){
        try {
            StudentResource studentResource = userClient.getStudentByUserId(query.userId()).getBody();

            evaluationClient.getAttendanceById(query.classroomId());

            if (studentResource != null) {
                List<Attendance> attendances = attendanceRepository.findAllByStudentIdAndClassroomId(studentResource.getId(), query.classroomId());

                attendances.forEach((attendance) -> {
                    try {
                        StudentResource studentResourceAttendance = userClient.getStudentById(attendance.getStudentId()).getBody();
                        attendance.setStudent(studentResourceAttendance);
                    } catch (Exception e) {
                        StudentResource studentResourceAttendance = StudentResource.builder().build();
                        attendance.setStudent(studentResourceAttendance);
                    }

                    try {
                        TeacherResource teacherResourceAttendance = userClient.getTeacherById(attendance.getTeacherId()).getBody();
                        attendance.setTeacher(teacherResourceAttendance);
                    } catch (Exception e) {
                        TeacherResource teacherResourceAttendance = TeacherResource.builder().build();
                        attendance.setTeacher(teacherResourceAttendance);
                    }
                });
                return attendances;
            } else {
                throw new RuntimeException("Error al validar estudiante.");
            }
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("El estudiante o la clase no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar estudiante o la clase.");
        }
    }
}
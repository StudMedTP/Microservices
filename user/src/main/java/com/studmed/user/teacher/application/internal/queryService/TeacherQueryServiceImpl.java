package com.studmed.user.teacher.application.internal.queryService;

import com.studmed.user.coordinator.infraestructure.persistance.jpa.respositories.CoordinatorRepository;
import com.studmed.user.medical_center.infraestructure.persistance.jpa.respositories.MedicalCenterRepository;
import com.studmed.user.speciality.infraestructure.persistance.jpa.respositories.SpecialityRepository;
import com.studmed.user.teacher.domain.model.aggregates.Teacher;
import com.studmed.user.teacher.domain.model.queries.GetTeacherByIdAndDailyCodeQuery;
import com.studmed.user.teacher.domain.model.queries.GetTeacherByIdQuery;
import com.studmed.user.teacher.domain.service.TeacherQueryService;
import com.studmed.user.teacher.infraestructure.persistance.jpa.respositories.TeacherRepository;
import com.studmed.user.shared.exception.ResourceNotFoundException;
import com.studmed.user.user.infraestructure.persistance.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherQueryServiceImpl implements TeacherQueryService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final MedicalCenterRepository medicalCenterRepository;
    private final SpecialityRepository specialityRepository;
    private final CoordinatorRepository coordinatorRepository;

    public TeacherQueryServiceImpl(TeacherRepository teacherRepository, UserRepository userRepository,
                                   MedicalCenterRepository medicalCenterRepository, SpecialityRepository specialityRepository,
                                   CoordinatorRepository coordinatorRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.medicalCenterRepository = medicalCenterRepository;
        this.specialityRepository = specialityRepository;
        this.coordinatorRepository = coordinatorRepository;
    }

    @Override
    public Teacher handle(GetTeacherByIdQuery query) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(query.id());

        if (teacherOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró docente");
        }

        Teacher teacher = teacherOptional.get();
        userRepository.findById(teacher.getUser().getId()).ifPresent(teacher::setUser);
        medicalCenterRepository.findById(teacher.getMedicalCenter().getId()).ifPresent(teacher::setMedicalCenter);
        specialityRepository.findById(teacher.getSpeciality().getId()).ifPresent(teacher::setSpeciality);
        coordinatorRepository.findById(teacher.getCoordinator().getId()).ifPresent(teacher::setCoordinator);

        return teacher;
    }

    @Override
    public Teacher handle(GetTeacherByIdAndDailyCodeQuery query) {
        Optional<Teacher> teacherOptional = teacherRepository.findByDailyCodeAndId(query.dailyCode(), query.id());

        if (teacherOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró docente");
        }

        Teacher teacher = teacherOptional.get();
        userRepository.findById(teacher.getUser().getId()).ifPresent(teacher::setUser);
        medicalCenterRepository.findById(teacher.getMedicalCenter().getId()).ifPresent(teacher::setMedicalCenter);
        specialityRepository.findById(teacher.getSpeciality().getId()).ifPresent(teacher::setSpeciality);
        coordinatorRepository.findById(teacher.getCoordinator().getId()).ifPresent(teacher::setCoordinator);

        return teacher;
    }
}
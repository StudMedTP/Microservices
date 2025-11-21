package com.studmed.user.teacher.application.internal.commandService;

import com.studmed.user.coordinator.domain.model.aggregates.Coordinator;
import com.studmed.user.coordinator.infraestructure.persistance.jpa.respositories.CoordinatorRepository;
import com.studmed.user.medical_center.domain.model.aggregates.MedicalCenter;
import com.studmed.user.medical_center.infraestructure.persistance.jpa.respositories.MedicalCenterRepository;
import com.studmed.user.speciality.domain.model.aggregates.Speciality;
import com.studmed.user.speciality.infraestructure.persistance.jpa.respositories.SpecialityRepository;
import com.studmed.user.teacher.domain.model.aggregates.Teacher;
import com.studmed.user.teacher.domain.model.commands.CloseClassByIdCommand;
import com.studmed.user.teacher.domain.model.commands.CreateTeacherCommand;
import com.studmed.user.teacher.domain.model.commands.OpenClassByIdCommand;
import com.studmed.user.teacher.domain.service.TeacherCommandService;
import com.studmed.user.teacher.infraestructure.persistance.jpa.respositories.TeacherRepository;
import com.studmed.user.user.domain.model.aggregates.User;
import com.studmed.user.user.infraestructure.persistance.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class TeacherCommandServiceImpl implements TeacherCommandService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final MedicalCenterRepository medicalCenterRepository;
    private final SpecialityRepository specialityRepository;
    private final CoordinatorRepository coordinatorRepository;

    public TeacherCommandServiceImpl(TeacherRepository teacherRepository, UserRepository userRepository,
                                     MedicalCenterRepository medicalCenterRepository, SpecialityRepository specialityRepository,
                                     CoordinatorRepository coordinatorRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.medicalCenterRepository = medicalCenterRepository;
        this.specialityRepository = specialityRepository;
        this.coordinatorRepository = coordinatorRepository;
    }

    @Override
    public Long handle(CreateTeacherCommand command) {
        Optional<User> userOptional = userRepository.findById(command.userId());

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Existe un error en los datos ingresados");
        }

        Optional<MedicalCenter> medicalCenterOptional = medicalCenterRepository.findById(command.medicalCenterId());

        if (medicalCenterOptional.isEmpty()) {
            throw new IllegalArgumentException("Existe un error en los datos ingresados");
        }

        Optional<Speciality> specialityOptional = specialityRepository.findById(command.specialityId());

        if (specialityOptional.isEmpty()) {
            throw new IllegalArgumentException("Existe un error en los datos ingresados");
        }

        Optional<Coordinator> coordinatorOptional = coordinatorRepository.findById(command.coordinatorId());

        if (coordinatorOptional.isEmpty()) {
            throw new IllegalArgumentException("Existe un error en los datos ingresados");
        }

        if (teacherRepository.existsByTeacherCode(command.teacherCode())) {
            throw new IllegalArgumentException("Ya existe un docente con ese código");
        }

        Teacher teacher = new Teacher(command, userOptional.get(), medicalCenterOptional.get(), specialityOptional.get(), coordinatorOptional.get());
        return teacherRepository.save(teacher).getId();
    }

    @Override
    public Long handle(OpenClassByIdCommand command) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(command.teacherId());

        if (teacherOptional.isEmpty()) {
            throw new IllegalArgumentException("No se encontró docente");
        }

        Teacher teacher = teacherOptional.get();

        Random random = new Random();
        Long code = 1000 + random.nextLong(9000);
        teacher.setDailyCode(String.valueOf(code));

        return teacherRepository.save(teacher).getId();
    }

    @Override
    public Long handle(CloseClassByIdCommand command) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(command.teacherId());

        if (teacherOptional.isEmpty()) {
            throw new IllegalArgumentException("No se encontró docente");
        }

        Teacher teacher = teacherOptional.get();

        teacher.setDailyCode(null);

        return teacherRepository.save(teacher).getId();
    }
}
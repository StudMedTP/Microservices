package com.studmed.user.coordinator.application.internal.commandService;

import com.studmed.user.coordinator.domain.model.aggregates.Coordinator;
import com.studmed.user.coordinator.domain.model.commands.CreateCoordinatorCommand;
import com.studmed.user.coordinator.domain.service.CoordinatorCommandService;
import com.studmed.user.coordinator.infraestructure.persistance.jpa.respositories.CoordinatorRepository;
import com.studmed.user.user.domain.model.aggregates.User;
import com.studmed.user.user.infraestructure.persistance.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoordinatorCommandServiceImpl implements CoordinatorCommandService {

    private final CoordinatorRepository coordinatorRepository;
    private final UserRepository userRepository;

    public CoordinatorCommandServiceImpl(CoordinatorRepository coordinatorRepository, UserRepository userRepository) {
        this.coordinatorRepository = coordinatorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateCoordinatorCommand command) {
        Optional<User> userOptional = userRepository.findById(command.userId());

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Existe un error en los datos ingresados");
        }

        if (coordinatorRepository.existsByCoordinatorCode(command.coordinatorCode())) {
            throw new IllegalArgumentException("Ya existe un coordinador con ese código");
        }

        Coordinator coordinator = new Coordinator(command, userOptional.get());
        return coordinatorRepository.save(coordinator).getId();
    }
}
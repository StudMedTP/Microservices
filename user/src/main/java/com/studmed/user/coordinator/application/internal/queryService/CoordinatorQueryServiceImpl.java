package com.studmed.user.coordinator.application.internal.queryService;

import com.studmed.user.shared.exception.ResourceNotFoundException;
import com.studmed.user.coordinator.domain.model.aggregates.Coordinator;
import com.studmed.user.coordinator.domain.model.queries.GetCoordinatorByIdQuery;
import com.studmed.user.coordinator.domain.service.CoordinatorQueryService;
import com.studmed.user.coordinator.infraestructure.persistance.jpa.respositories.CoordinatorRepository;
import com.studmed.user.user.infraestructure.persistance.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoordinatorQueryServiceImpl implements CoordinatorQueryService {

    private final CoordinatorRepository coordinatorRepository;
    private final UserRepository userRepository;

    public CoordinatorQueryServiceImpl(CoordinatorRepository coordinatorRepository, UserRepository userRepository) {
        this.coordinatorRepository = coordinatorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Coordinator handle(GetCoordinatorByIdQuery query) {
        Optional<Coordinator> coordinatorOptional = coordinatorRepository.findById(query.id());

        if (coordinatorOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró coordinador");
        }

        Coordinator coordinator = coordinatorOptional.get();
        userRepository.findById(coordinator.getUser().getId()).ifPresent(coordinator::setUser);

        return coordinator;
    }
}
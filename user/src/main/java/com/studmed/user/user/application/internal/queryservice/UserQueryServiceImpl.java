package com.studmed.user.user.application.internal.queryservice;

import com.studmed.user.shared.exception.ResourceNotFoundException;
import com.studmed.user.user.domain.model.aggregates.User;
import com.studmed.user.user.domain.model.queries.GetAllUserQuery;
import com.studmed.user.user.domain.model.queries.GetUserByIdQuery;
import com.studmed.user.user.domain.model.queries.GetUserByUsernameAndPassword;
import com.studmed.user.user.domain.service.UserQueryService;
import com.studmed.user.user.infraestructure.persistance.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(GetUserByIdQuery query) {
        Optional<User> userOptional = userRepository.findById(query.id());

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró usuario");
        }

        return userOptional.get();
    }

    @Override
    public List<User> handle(GetAllUserQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByUsernameAndPassword query) {
        return userRepository.findByEmailAndPassword(query.username(), query.password());
    }
}
package com.studmed.user.user.application.internal.queryservice;

import com.studmed.user.shared.RabbitConfiguration;
import com.studmed.user.shared.exception.BadRequestException;
import com.studmed.user.shared.exception.ResourceNotFoundException;
import com.studmed.user.user.domain.model.aggregates.User;
import com.studmed.user.user.domain.model.queries.GetAllUserQuery;
import com.studmed.user.user.domain.model.queries.GetUserByIdQuery;
import com.studmed.user.user.domain.model.queries.GetUserByCredentials;
import com.studmed.user.user.domain.service.UserQueryService;
import com.studmed.user.user.infraestructure.persistance.jpa.repositories.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;
    private final PasswordEncoder passwordEncoder;

    public UserQueryServiceImpl(UserRepository userRepository, RabbitTemplate rabbitTemplate, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.passwordEncoder = passwordEncoder;
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
        rabbitTemplate.convertAndSend(
                RabbitConfiguration.EXCHANGE,
                RabbitConfiguration.ROUTING_KEY,
                "Hola desde el query service");

        return userRepository.findAll();
    }

    @Override
    public User handle(GetUserByCredentials query) {
        Optional<User> userOptional = userRepository.findByEmail(query.email());

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró usuario");
        }

        if (!passwordEncoder.matches(query.password(), userOptional.get().getPassword())) {
            throw new BadRequestException("Credenciales Incorrectas");
        } else {
            return userOptional.get();
        }
    }
}
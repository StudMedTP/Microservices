package com.studmed.user.user.application.internal.commandservice;

import com.studmed.user.user.domain.model.aggregates.User;
import com.studmed.user.user.domain.model.commands.CreateUserCommand;
import com.studmed.user.user.domain.model.commands.DeleteUserCommand;
import com.studmed.user.user.domain.model.commands.UpdateUserCommand;
import com.studmed.user.user.domain.service.UserCommandService;
import com.studmed.user.user.infraestructure.persistance.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateUserCommand command) {
        if (userRepository.existsByEmail(command.email())){
            throw new IllegalArgumentException("Ya existe un usuario con ese correo");
        }

        User user = new User(command);
        return userRepository.save(user).getId();
    }

    @Override
    public Long handle (UpdateUserCommand command) {
        Optional<User> userOptional = userRepository.findById(command.id());

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No se encontró usuario");
        }

        if (userRepository.existsByEmailAndIdIsNot(command.email(), command.id())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo");
        }

        User user = userOptional.get();

        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
        user.setEmail(command.email());
        user.setPassword(command.password());
        user.setUserImg(command.userImg());

        return userRepository.save(user).getId();
    }

    @Override
    public void handle (DeleteUserCommand command) {
        if(!userRepository.existsById(command.id())) {
            throw new IllegalArgumentException("El usuario no existe");
        }

        userRepository.deleteById(command.id());
    }
}
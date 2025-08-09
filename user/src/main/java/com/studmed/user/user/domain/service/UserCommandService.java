package com.studmed.user.user.domain.service;

import com.studmed.user.user.domain.model.commands.CreateUserCommand;
import com.studmed.user.user.domain.model.commands.DeleteUserCommand;
import com.studmed.user.user.domain.model.commands.UpdateUserCommand;

public interface UserCommandService {
    Long handle (CreateUserCommand command);
    Long handle (UpdateUserCommand command);
    void handle (DeleteUserCommand command);
}
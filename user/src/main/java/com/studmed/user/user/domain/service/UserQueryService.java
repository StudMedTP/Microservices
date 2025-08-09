package com.studmed.user.user.domain.service;

import com.studmed.user.user.domain.model.aggregates.User;
import com.studmed.user.user.domain.model.queries.GetAllUserQuery;
import com.studmed.user.user.domain.model.queries.GetUserByIdQuery;
import com.studmed.user.user.domain.model.queries.GetUserByUsernameAndPassword;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUserQuery query);
    User handle(GetUserByIdQuery query);
    Optional <User> handle(GetUserByUsernameAndPassword query);
}
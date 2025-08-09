package com.studmed.user.user.domain.service;

import com.studmed.user.user.domain.model.aggregates.User;
import com.studmed.user.user.domain.model.queries.GetAllUserQuery;
import com.studmed.user.user.domain.model.queries.GetUserByIdQuery;
import com.studmed.user.user.domain.model.queries.GetUserByEmail;

import java.util.List;

public interface UserQueryService {
    List<User> handle(GetAllUserQuery query);
    User handle(GetUserByIdQuery query);
    User handle(GetUserByEmail query);
}
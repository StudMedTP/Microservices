package com.studmed.user.student.domain.service;

import com.studmed.user.student.domain.model.aggregates.Student;
import com.studmed.user.student.domain.model.queries.GetStudentByIdQuery;

import java.util.Optional;

public interface StudentQueryService {
    Optional<Student> handle(GetStudentByIdQuery query);
}
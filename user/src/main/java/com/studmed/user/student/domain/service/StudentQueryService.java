package com.studmed.user.student.domain.service;

import com.studmed.user.student.domain.model.aggregates.Student;
import com.studmed.user.student.domain.model.queries.GetStudentByIdQuery;

public interface StudentQueryService {
    Student handle(GetStudentByIdQuery query);
}
package com.studmed.user.student.domain.service;

import com.studmed.user.student.domain.model.aggregates.Student;
import com.studmed.user.student.domain.model.queries.GetStudentByIdQuery;
import com.studmed.user.student.domain.model.queries.GetStudentByUserIdQuery;

public interface StudentQueryService {
    Student handle(GetStudentByIdQuery query);
    Student handle(GetStudentByUserIdQuery query);
}
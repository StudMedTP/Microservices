package com.studmed.user.student.domain.service;

import com.studmed.user.student.domain.model.aggregates.Student;
import com.studmed.user.student.domain.model.queries.GetAllStudentsByTeacherIdQuery;
import com.studmed.user.student.domain.model.queries.GetStudentByIdQuery;
import com.studmed.user.student.domain.model.queries.GetStudentByUserIdQuery;

import java.util.List;

public interface StudentQueryService {
    Student handle(GetStudentByIdQuery query);
    Student handle(GetStudentByUserIdQuery query);
    List<Student> handle(GetAllStudentsByTeacherIdQuery query);
}
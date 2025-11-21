package com.studmed.user.teacher.domain.service;

import com.studmed.user.teacher.domain.model.aggregates.Teacher;
import com.studmed.user.teacher.domain.model.queries.GetTeacherByIdAndDailyCodeQuery;
import com.studmed.user.teacher.domain.model.queries.GetTeacherByIdQuery;

public interface TeacherQueryService {
    Teacher handle(GetTeacherByIdQuery query);
    Teacher handle(GetTeacherByIdAndDailyCodeQuery query);
}
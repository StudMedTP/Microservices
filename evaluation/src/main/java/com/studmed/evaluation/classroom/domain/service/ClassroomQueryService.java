package com.studmed.evaluation.classroom.domain.service;

import com.studmed.evaluation.classroom.domain.model.aggregate.Classroom;
import com.studmed.evaluation.classroom.domain.model.queries.GetAllClassroomByUserIdQuery;
import com.studmed.evaluation.classroom.domain.model.queries.GetClassroomByIdQuery;

import java.util.List;

public interface ClassroomQueryService {
    Classroom handle (GetClassroomByIdQuery query);
    List<Classroom> handle (GetAllClassroomByUserIdQuery query);
}
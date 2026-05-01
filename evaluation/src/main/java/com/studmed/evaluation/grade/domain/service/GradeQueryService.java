package com.studmed.evaluation.grade.domain.service;

import com.studmed.evaluation.grade.domain.model.aggregate.Grade;
import com.studmed.evaluation.grade.domain.model.queries.GetAllGradeByClassStudentIdQuery;
import com.studmed.evaluation.grade.domain.model.queries.GetGradeByIdQuery;

import java.util.List;

public interface GradeQueryService {
    Grade handle (GetGradeByIdQuery query);
    List<Grade> handle (GetAllGradeByClassStudentIdQuery query);
}
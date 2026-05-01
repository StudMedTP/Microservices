package com.studmed.evaluation.classroomstudent.domain.service;

import com.studmed.evaluation.classroomstudent.domain.model.aggregate.ClassroomStudent;
import com.studmed.evaluation.classroomstudent.domain.model.queries.GetAllClassroomStudentByClassIdQuery;
import com.studmed.evaluation.classroomstudent.domain.model.queries.GetAllClassroomStudentByUserIdQuery;
import com.studmed.evaluation.classroomstudent.domain.model.queries.GetClassroomStudentByIdQuery;

import java.util.List;

public interface ClassroomStudentQueryService {
    ClassroomStudent handle (GetClassroomStudentByIdQuery query);
    List<ClassroomStudent> handle (GetAllClassroomStudentByUserIdQuery query);
    List<ClassroomStudent> handle (GetAllClassroomStudentByClassIdQuery query);
}
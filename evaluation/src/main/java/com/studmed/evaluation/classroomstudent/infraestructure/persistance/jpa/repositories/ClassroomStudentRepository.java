package com.studmed.evaluation.classroomstudent.infraestructure.persistance.jpa.repositories;

import com.studmed.evaluation.classroomstudent.domain.model.aggregate.ClassroomStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomStudentRepository extends JpaRepository<ClassroomStudent, Long> {
    List<ClassroomStudent> findAllByClassroom_Id(Long classroomId);
    List<ClassroomStudent> findAllByStudentId(Long studentId);

    Boolean existsByClassroom_IdAndStudentId(Long classroomId, Long studentId);
}
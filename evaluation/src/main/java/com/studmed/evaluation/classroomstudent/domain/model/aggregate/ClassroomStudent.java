package com.studmed.evaluation.classroomstudent.domain.model.aggregate;

import com.studmed.evaluation.classroom.domain.model.aggregate.Classroom;
import com.studmed.evaluation.classroom.domain.model.client.StudentResource;
import com.studmed.evaluation.classroomstudent.domain.model.commands.CreateClassroomStudentCommand;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ClassroomStudent")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassroomStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classroomId")
    private Classroom classroom;

    @Column(name = "student_id")
    private Long studentId;

    @Transient
    private StudentResource student;

    public ClassroomStudent(CreateClassroomStudentCommand command, Classroom classroom){
        this();
        this.studentId = command.studentId();
        this.classroom = classroom;
    }
}
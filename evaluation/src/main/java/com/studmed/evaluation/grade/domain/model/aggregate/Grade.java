package com.studmed.evaluation.grade.domain.model.aggregate;

import com.studmed.evaluation.classroomstudent.domain.model.aggregate.ClassroomStudent;

import com.studmed.evaluation.grade.domain.model.commands.CreateGradeCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Grade")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long value;

    private String description;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassroomStudent classroomStudent;

    public Grade(CreateGradeCommand command, ClassroomStudent classroomStudent){
        this();
        this.value = command.value();
        this.description = command.description();
        this.classroomStudent = classroomStudent;
    }
}
package com.studmed.user.student.domain.model.aggregates;

import com.studmed.user.student.domain.model.commands.CreateStudentCommand;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentCode;

    public Student (CreateStudentCommand command){
        this();
        this.studentCode = command.studentCode();
    }
}
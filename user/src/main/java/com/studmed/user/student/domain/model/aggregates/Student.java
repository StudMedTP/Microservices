package com.studmed.user.student.domain.model.aggregates;

import com.studmed.user.student.domain.model.commands.CreateStudentCommand;
import com.studmed.user.user.domain.model.aggregates.User;
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

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public Student (CreateStudentCommand command, User user){
        this();
        this.studentCode = command.studentCode();
        this.user = user;
    }
}
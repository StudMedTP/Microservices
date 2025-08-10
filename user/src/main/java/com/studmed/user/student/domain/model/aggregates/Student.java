package com.studmed.user.student.domain.model.aggregates;

import com.studmed.user.student.domain.model.commands.CreateStudentCommand;
import com.studmed.user.teacher.domain.model.aggregates.Teacher;
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

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    public Student (CreateStudentCommand command, User user, Teacher teacher){
        this();
        this.studentCode = command.studentCode();
        this.user = user;
        this.teacher = teacher;
    }
}
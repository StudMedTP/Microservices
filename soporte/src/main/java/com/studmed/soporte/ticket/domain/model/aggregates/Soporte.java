package com.studmed.soporte.ticket.domain.model.aggregates;

import com.studmed.soporte.ticket.domain.model.client.StudentResource;
import com.studmed.soporte.ticket.domain.model.client.TeacherResource;
import com.studmed.soporte.ticket.domain.model.commands.CreateSoporteCommand;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "Soporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Soporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String message;

    @Column(name = "student_id")
    private Long studentId;

    @Transient
    private StudentResource student;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Transient
    private TeacherResource teacher;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    public Soporte(CreateSoporteCommand command) {
        this();
        this.title = command.title();
        this.message = command.message();
        this.studentId = command.studentId();
        this.teacherId = command.teacherId();
    }
}
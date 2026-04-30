package com.studmed.attendance.record.domain.model.aggregates;

import com.studmed.attendance.record.domain.model.client.TeacherResource;
import com.studmed.attendance.record.domain.model.client.StudentResource;
import com.studmed.attendance.record.domain.model.commands.CreateAttendanceCommand;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "Attendance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;

    private Double longitude;

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

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public Attendance(CreateAttendanceCommand command){
        this();
        this.studentId = command.studentId();
        this.teacherId = command.teacherId();
        this.latitude = command.latitude();
        this.longitude = command.longitude();
    }
}
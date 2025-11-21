package com.studmed.attendance.record.domain.model.aggregates;

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

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "medical_center_id")
    private Long medicalCenterId;

    private String status;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public Attendance(CreateAttendanceCommand command){
        this();
        this.studentId = command.studentId();
        this.medicalCenterId = command.medicalCenterId();
        this.status = command.status();
    }
}
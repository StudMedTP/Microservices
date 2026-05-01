package com.studmed.evaluation.classroom.domain.model.aggregate;

import com.studmed.evaluation.classroom.domain.model.client.MedicalCenterResource;
import com.studmed.evaluation.classroom.domain.model.client.TeacherResource;
import com.studmed.evaluation.classroom.domain.model.commands.CreateClassroomCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "Classroom")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "medical_center_id")
    private Long medicalCenterId;

    @Transient
    private MedicalCenterResource medicalCenter;

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

    public Classroom(CreateClassroomCommand command){
        this();
        this.name = command.name();
        this.teacherId = command.teacherId();
        this.medicalCenterId = command.medicalCenterId();
    }
}
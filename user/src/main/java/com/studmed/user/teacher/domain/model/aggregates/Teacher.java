package com.studmed.user.teacher.domain.model.aggregates;

import com.studmed.user.coordinator.domain.model.aggregates.Coordinator;
import com.studmed.user.medical_center.domain.model.aggregates.MedicalCenter;
import com.studmed.user.speciality.domain.model.aggregates.Speciality;
import com.studmed.user.teacher.domain.model.commands.CreateTeacherCommand;
import com.studmed.user.user.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teacherCode;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "medicalCenterId")
    private MedicalCenter medicalCenter;

    @ManyToOne
    @JoinColumn(name = "specialityId")
    private Speciality speciality;

    @ManyToOne
    @JoinColumn(name = "coordinatorId")
    private Coordinator coordinator;

    public Teacher(CreateTeacherCommand command, User user, MedicalCenter medicalCenter, Speciality speciality, Coordinator coordinator){
        this();
        this.teacherCode = command.teacherCode();
        this.user = user;
        this.medicalCenter = medicalCenter;
        this.speciality = speciality;
        this.coordinator = coordinator;
    }
}
package com.studmed.evaluation.clinichistory.domain.model.aggregate;

import com.studmed.evaluation.classroom.domain.model.client.StudentResource;
import com.studmed.evaluation.clinichistory.domain.model.commands.CreateClinicHistoryCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ClinicHistory")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medical_history_number")
    private String medicalHistoryNumber;

    private Integer age;

    private String sex;

    private String mainDiagnosis;

    private String treatment;

    private String analysis;

    @Column(name = "student_id")
    private Long studentId;

    @Transient
    private StudentResource student;

    public ClinicHistory(CreateClinicHistoryCommand command) {
        this.medicalHistoryNumber = command.medicalHistoryNumber();
        this.age = command.age();
        this.sex = command.sex();
        this.mainDiagnosis = command.mainDiagnosis();
        this.treatment = command.treatment();
        this.analysis = command.analysis();
        this.studentId = command.studentId();
    }
}

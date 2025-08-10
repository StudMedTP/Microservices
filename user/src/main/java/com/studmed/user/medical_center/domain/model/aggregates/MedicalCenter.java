package com.studmed.user.medical_center.domain.model.aggregates;

import com.studmed.user.medical_center.domain.model.commands.CreateMedicalCenterCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MedicalCenter")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public MedicalCenter(CreateMedicalCenterCommand command){
        this();
        this.name = command.name();
    }
}
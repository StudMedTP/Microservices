package com.studmed.user.speciality.domain.model.aggregates;

import com.studmed.user.speciality.domain.model.commands.CreateSpecialityCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Speciality")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Speciality(CreateSpecialityCommand command){
        this();
        this.name = command.name();
    }
}
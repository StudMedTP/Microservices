package com.studmed.user.coordinator.domain.model.aggregates;

import com.studmed.user.coordinator.domain.model.commands.CreateCoordinatorCommand;
import com.studmed.user.user.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Coordinator")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String coordinatorCode;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public Coordinator(CreateCoordinatorCommand command, User user){
        this();
        this.coordinatorCode = command.coordinatorCode();
        this.user = user;
    }
}
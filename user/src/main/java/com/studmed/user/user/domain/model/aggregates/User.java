package com.studmed.user.user.domain.model.aggregates;

import com.studmed.user.user.domain.model.commands.CreateUserCommand;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;

    public User (CreateUserCommand command){
        this();
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.email = command.email();
        this.password = command.password();
        this.role = command.role();
    }
}
package com.studmed.user.user.domain.model.aggregates;

import com.studmed.user.user.domain.model.commands.CreateUserCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "User")
public class User extends AbstractAggregateRoot<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Setter
    @Getter
    private String rol;

    @Setter
    @Getter
    private String firstName;

    @Setter
    @Getter
    private String lastName;

    @Setter
    @Getter
    private String email;

    @Setter
    @Getter
    private String userName;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private String phoneNumber;

    @Setter
    @Getter
    private String userImg;

    public User() {
        this.rol = Strings.EMPTY;
        this.firstName = Strings.EMPTY;
        this.lastName = Strings.EMPTY;
        this.email = Strings.EMPTY;
        this.userName = Strings.EMPTY;
        this.password = Strings.EMPTY;
        this.phoneNumber = Strings.EMPTY;
        this.userImg = Strings.EMPTY;
    }

    public User(String rol, String firstName, String lastName, String email, String userName, String password, String phoneNumber, String userImg) {
        this.rol = rol;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userImg = userImg;
    }

    public User (CreateUserCommand command){
        this();
        this.rol = command.rol();
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.email = command.email();
        this.userName = command.userName();
        this.password = command.password();
        this.phoneNumber = command.phoneNumber();
        this.userImg = command.userImg();
    }

    public User updateUser(String rol, String firstName, String lastName, String email, String userName, String password, String phoneNumber, String userImg) {
        this.rol = rol;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userImg = userImg;
        return this;
    }
}
package com.studmed.attendance.domain.model.aggregates;

import com.studmed.attendance.domain.model.commands.CreateAttendanceCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "Attendance")
public class Attendance extends AbstractAggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String attendaceDate;

    @Getter
    private String registrationTime;

    @Getter
    private String courseName;

    @Getter
    private String attendaceState;

    @Getter
    private String verificationToken;

    @Getter
    private String coordinates;

    public Attendance(){
        this.attendaceDate = Strings.EMPTY;
        this.registrationTime = Strings.EMPTY;
        this.courseName = Strings.EMPTY;
        this.attendaceState = Strings.EMPTY;
        this.verificationToken = Strings.EMPTY;
        this.coordinates = Strings.EMPTY;
    }

    public Attendance(String attendaceDate, String registrationTime, String courseName, String attendaceState, String verificationToken, String coordinates) {
        this.attendaceDate = attendaceDate;
        this.registrationTime = registrationTime;
        this.courseName = courseName;
        this.attendaceState = attendaceState;
        this.verificationToken = verificationToken;
        this.coordinates = coordinates;
    }

    public Attendance(CreateAttendanceCommand command){
        this();
        this.attendaceDate = command.attendaceDate();
        this.registrationTime = command.registrationTime();
        this.courseName = command.courseName();
        this.attendaceState = command.attendaceState();
        this.verificationToken = command.verificationToken();
        this.coordinates = command.coordinates();
    }

    public Attendance updateAttendance(String attendaceDate, String registrationTime, String courseName, String attendaceState, String verificationToken, String coordinates){
        this.attendaceDate = attendaceDate;
        this.registrationTime = registrationTime;
        this.courseName = courseName;
        this.attendaceState = attendaceState;
        this.verificationToken = verificationToken;
        this.coordinates = coordinates;
        return this;
    }
}
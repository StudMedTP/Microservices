package com.studmed.soporte.domain.model.aggregates;

import com.studmed.soporte.domain.model.commands.CreateSoporteCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "Soporte")
public class Soporte extends AbstractAggregateRoot<Soporte> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String origin;

    @Getter
    private String destination;

    @Getter
    private String date;

    public Soporte() {
        this.origin = Strings.EMPTY;
        this.destination = Strings.EMPTY;
        this.date = Strings.EMPTY;
    }

    public Soporte(String origin, String destination, String date) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }

    public Soporte(CreateSoporteCommand command) {
        this();
        this.origin = command.origin();
        this.destination = command.destination();
        this.date = command.date();
    }

    public Soporte updateSoporte(String origin, String destination, String date) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        return this;
    }
}
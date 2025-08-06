package com.studmed.soporte.domain.model.aggregates;

import com.studmed.soporte.domain.model.client.User;
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
    private String ticketCreationTime;

    @Getter
    private String ticketSentTitle;

    @Getter
    private String ticketSentMessage;

    @Getter
    private String ticketState;

    @Getter
    private String ticketResponseTitle;

    @Getter
    private String ticketResponseMessage;

    @Getter
    private String ticketResponseTime;

    @Getter
    @Column(name = "user_id")
    private Long userId;

    @Transient
    private User user;

    public Soporte() {
        this.ticketCreationTime = Strings.EMPTY;
        this.ticketSentTitle = Strings.EMPTY;
        this.ticketSentMessage = Strings.EMPTY;
        this.ticketState = Strings.EMPTY;
        this.ticketResponseTitle = Strings.EMPTY;
        this.ticketResponseMessage = Strings.EMPTY;
        this.ticketResponseTime = Strings.EMPTY;
        this.userId = 0L;
    }

    public Soporte(String ticketCreationTime, String ticketSentTitle, String ticketSentMessage, String ticketState, String ticketResponseTitle, String ticketResponseMessage, String ticketResponseTime, Long userId) {
        this.ticketCreationTime = ticketCreationTime;
        this.ticketSentTitle = ticketSentTitle;
        this.ticketSentMessage = ticketSentMessage;
        this.ticketState = ticketState;
        this.ticketResponseTitle = ticketResponseTitle;
        this.ticketResponseMessage = ticketResponseMessage;
        this.ticketResponseTime = ticketResponseTime;
        this.userId = userId;
    }

    public Soporte(CreateSoporteCommand command) {
        this();
        this.ticketCreationTime = command.ticketCreationTime();
        this.ticketSentTitle = command.ticketSentTitle();
        this.ticketSentMessage = command.ticketSentMessage();
        this.ticketState = command.ticketState();
        this.ticketResponseTitle = command.ticketResponseTitle();
        this.ticketResponseMessage = command.ticketResponseMessage();
        this.ticketResponseTime = command.ticketResponseTime();
        this.userId = command.userId();
    }

    public Soporte updateSoporte(String ticketCreationTime, String ticketSentTitle, String ticketSentMessage, String ticketState, String ticketResponseTitle, String ticketResponseMessage, String ticketResponseTime) {
        this.ticketCreationTime = ticketCreationTime;
        this.ticketSentTitle = ticketSentTitle;
        this.ticketSentMessage = ticketSentMessage;
        this.ticketState = ticketState;
        this.ticketResponseTitle = ticketResponseTitle;
        this.ticketResponseMessage = ticketResponseMessage;
        this.ticketResponseTime = ticketResponseTime;
        return this;
    }
}
package com.studmed.notification.domain.model.aggregates;


import com.studmed.notification.domain.model.commands.CreateNotificationCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;


@Entity
@Table(name = "Notification")


public class Notification extends AbstractAggregateRoot<Notification> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String title;

    @Getter
    private String message;

    @Getter
    private String time;

    @Getter
    private String notificationType;

    public Notification() {

        this.title = Strings.EMPTY;
        this.message = Strings.EMPTY;
        this.time = Strings.EMPTY;
        this.notificationType = Strings.EMPTY;
    }

    public Notification(String title, String message, String time, String notificationType) {

        this.title = title;
        this.message = message;
        this.time = time;
        this.notificationType = notificationType;

    }

    public Notification(CreateNotificationCommand command){

        this();
        this.title = command.title();
        this.message = command.message();
        this.time = command.time();
        this.notificationType = command.notificationType();
    }

    public Notification updateNotification(String product, String productQuantity, String cartTotal, String notificationType) {

        this.title = product;
        this.message = productQuantity;
        this.time = cartTotal;
        this.notificationType = notificationType;
        return this;

    }

}

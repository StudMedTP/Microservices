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
    private String product;

    @Getter
    private String productQuantity;

    @Getter
    private String cartTotal;

    public Notification() {

        this.product = Strings.EMPTY;
        this.productQuantity = Strings.EMPTY;
        this.cartTotal = Strings.EMPTY;

    }

    public Notification(String product, String productQuantity, String cartTotal) {

        this.product = product;
        this.productQuantity = productQuantity;
        this.cartTotal = cartTotal;

    }

    public Notification(CreateNotificationCommand command){

        this();
        this.product = command.product();
        this.productQuantity = command.productQuantity();
        this.cartTotal = command.cartTotal();

    }

    public Notification updateNotification(String product, String productQuantity, String cartTotal) {

        this.product = product;
        this.productQuantity = productQuantity;
        this.cartTotal = cartTotal;
        return this;

    }

}

package com.studmed.notification.domain.model.commands;

public record CreateNotificationCommand(String product,
                                        String productQuantity,
                                        String cartTotal) {

}
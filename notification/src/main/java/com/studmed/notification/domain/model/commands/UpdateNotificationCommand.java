package com.studmed.notification.domain.model.commands;

public record UpdateNotificationCommand(Long id,
                                        String product,
                                        String productQuantity,
                                        String cartTotal){

}
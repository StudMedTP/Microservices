package com.studmed.notification.interfaces.rest.resource;

public record CreateNotificationResource(String product,
                                         String productQuantity,
                                         String cartTotal) {

}

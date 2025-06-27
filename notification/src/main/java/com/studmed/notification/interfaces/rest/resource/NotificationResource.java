package com.studmed.notification.interfaces.rest.resource;

public record NotificationResource(Long id,
                                   String product,
                                   String productQuantity,
                                   String cartTotal) {

}

package com.studmed.attendance.interfaces.rest.resource;

public record UpdateAttendanceResource(Double orderNumber,
                                       String orderDate,
                                       String waitingTime,
                                       Double totalPrice,
                                       String orderStatus,
                                       String paymentMethod,
                                       Double paymentAmount) {
}
package com.studmed.attendance.domain.model.commands;

public record CreateAttendanceCommand(Double orderNumber,
                                      String orderDate,
                                      String waitingTime,
                                      Double totalPrice,
                                      String orderStatus,
                                      String paymentMethod,
                                      Double paymentAmount) {
}
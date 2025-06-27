package com.studmed.attendance.interfaces.rest.transform;

import com.studmed.attendance.domain.model.aggregates.Attendance;
import com.studmed.attendance.interfaces.rest.resource.AttendanceResource;

public class AttendanceResourceFromEntityAssembler {
    public static AttendanceResource toResourceFromEntity(Attendance entity) {
        return new AttendanceResource(
                entity.getId(),
                entity.getOrderNumber(),
                entity.getOrderDate(),
                entity.getWaitingTime(),
                entity.getTotalPrice(),
                entity.getOrderStatus(),
                entity.getPaymentMethod(),
                entity.getPaymentAmount());
    }
}
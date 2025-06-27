package com.studmed.attendance.interfaces.rest.transform;

import com.studmed.attendance.domain.model.commands.CreateAttendanceCommand;
import com.studmed.attendance.interfaces.rest.resource.CreateAttendanceResource;

public class CreateAttendanceCommandFromResourceAssembler {
    public static CreateAttendanceCommand toCommandFromResource(CreateAttendanceResource resource) {
        return new CreateAttendanceCommand(
                resource.orderNumber(),
                resource.orderDate(),
                resource.waitingTime(),
                resource.totalPrice(),
                resource.orderStatus(),
                resource.paymentMethod(),
                resource.paymentAmount());
    }
}
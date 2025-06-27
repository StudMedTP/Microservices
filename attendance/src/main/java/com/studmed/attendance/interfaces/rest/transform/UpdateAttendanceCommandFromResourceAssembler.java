package com.studmed.attendance.interfaces.rest.transform;

import com.studmed.attendance.domain.model.commands.UpdateAttendanceCommand;
import com.studmed.attendance.interfaces.rest.resource.UpdateAttendanceResource;

public class UpdateAttendanceCommandFromResourceAssembler {
    public static UpdateAttendanceCommand toCommandFromResource(Long id, UpdateAttendanceResource resource){
        return new UpdateAttendanceCommand(
                id,
                resource.orderNumber(),
                resource.orderDate(),
                resource.waitingTime(),
                resource.totalPrice(),
                resource.orderStatus(),
                resource.paymentMethod(),
                resource.paymentAmount());
    }
}
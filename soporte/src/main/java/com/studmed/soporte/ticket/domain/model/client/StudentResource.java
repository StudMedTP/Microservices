package com.studmed.soporte.ticket.domain.model.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResource {
    private Long id;
    private String studentCode;
}

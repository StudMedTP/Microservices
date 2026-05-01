package com.studmed.evaluation.classroom.domain.model.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicalCenterResource {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
}

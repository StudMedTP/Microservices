package com.studmed.user.medical_center.domain.service;

import com.studmed.user.medical_center.domain.model.aggregates.MedicalCenter;
import com.studmed.user.medical_center.domain.model.queries.GetMedicalCenterByIdQuery;

public interface MedicalCenterQueryService {
    MedicalCenter handle(GetMedicalCenterByIdQuery query);
}
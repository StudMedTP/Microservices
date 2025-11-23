package com.studmed.user.medical_center.domain.service;

import com.studmed.user.medical_center.domain.model.aggregates.MedicalCenter;
import com.studmed.user.medical_center.domain.model.queries.GetAllMedicalCenters;
import com.studmed.user.medical_center.domain.model.queries.GetMedicalCenterByIdQuery;

import java.util.List;

public interface MedicalCenterQueryService {
    List<MedicalCenter> handle(GetAllMedicalCenters query);
    MedicalCenter handle(GetMedicalCenterByIdQuery query);
}
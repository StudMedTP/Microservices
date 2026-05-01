package com.studmed.evaluation.clinichistory.domain.service;

import com.studmed.evaluation.clinichistory.domain.model.aggregate.ClinicHistory;
import com.studmed.evaluation.clinichistory.domain.model.queries.GetAllClinicHistoriesByUserIdQuery;
import com.studmed.evaluation.clinichistory.domain.model.queries.GetClinicHistoryByIdQuery;

import java.util.List;

public interface ClinicHistoryQueryService {
    ClinicHistory handle(GetClinicHistoryByIdQuery query);
    List<ClinicHistory> handle(GetAllClinicHistoriesByUserIdQuery query);
}

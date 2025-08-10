package com.studmed.user.speciality.domain.service;

import com.studmed.user.speciality.domain.model.aggregates.Speciality;
import com.studmed.user.speciality.domain.model.queries.GetSpecialityByIdQuery;

public interface SpecialityQueryService {
    Speciality handle(GetSpecialityByIdQuery query);
}
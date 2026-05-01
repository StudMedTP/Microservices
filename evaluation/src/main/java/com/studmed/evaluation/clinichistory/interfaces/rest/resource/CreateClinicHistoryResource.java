package com.studmed.evaluation.clinichistory.interfaces.rest.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateClinicHistoryResource(
        @NotBlank
        String medicalHistoryNumber,
        @NotNull
        @Min(1)
        Integer age,
        @NotBlank
        String sex,
        @NotBlank
        String mainDiagnosis,
        @NotBlank
        String treatment,
        @NotBlank
        String analysis,
        @NotNull
        @Min(1)
        Long studentId
) {}

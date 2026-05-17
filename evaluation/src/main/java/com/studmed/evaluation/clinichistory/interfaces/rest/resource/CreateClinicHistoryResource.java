package com.studmed.evaluation.clinichistory.interfaces.rest.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateClinicHistoryResource(
        @NotBlank
        String medicalHistoryNumber,
        @NotNull
        @Min(1)
        Integer age,
        @NotBlank
        @Pattern(regexp = "[MF]", message = "El sexo solo puede ser 'M' o 'F'")
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

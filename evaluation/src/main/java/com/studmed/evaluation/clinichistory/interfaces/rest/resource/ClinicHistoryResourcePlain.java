package com.studmed.evaluation.clinichistory.interfaces.rest.resource;

public record ClinicHistoryResourcePlain(
        Long id,
        String medicalHistoryNumber,
        Integer age,
        String sex,
        String mainDiagnosis,
        String treatment,
        String analysis,
        Long studentId
) {}

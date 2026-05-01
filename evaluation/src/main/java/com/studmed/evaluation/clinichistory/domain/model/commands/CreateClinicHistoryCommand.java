package com.studmed.evaluation.clinichistory.domain.model.commands;

public record CreateClinicHistoryCommand(
        String medicalHistoryNumber,
        Integer age,
        String sex,
        String mainDiagnosis,
        String treatment,
        String analysis,
        Long studentId
) {}

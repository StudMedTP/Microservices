package com.studmed.evaluation.clinichistory.interfaces.rest.resource;

import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.StudentResource;

public record ClinicHistoryResource(
        Long id,
        String medicalHistoryNumber,
        Integer age,
        String sex,
        String mainDiagnosis,
        String treatment,
        String analysis,
        Long studentId,
        StudentResource studentResource
) {}

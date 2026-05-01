package com.studmed.evaluation.clinichistory.interfaces.rest.transform;

import com.studmed.evaluation.classroomstudent.interfaces.rest.resource.StudentResource;
import com.studmed.evaluation.clinichistory.domain.model.aggregate.ClinicHistory;
import com.studmed.evaluation.clinichistory.interfaces.rest.resource.ClinicHistoryResource;
import com.studmed.evaluation.clinichistory.interfaces.rest.resource.ClinicHistoryResourcePlain;

public class ClinicHistoryResourceFromEntityAssembler {
    public static ClinicHistoryResource toResourceFromEntity(ClinicHistory entity) {
        return new ClinicHistoryResource(
                entity.getId(),
                entity.getMedicalHistoryNumber(),
                entity.getAge(),
                entity.getSex(),
                entity.getMainDiagnosis(),
                entity.getTreatment(),
                entity.getAnalysis(),
                entity.getStudentId(),
                new StudentResource(
                        entity.getStudent().getId(),
                        entity.getStudent().getStudentCode()
                )
        );
    }

    public static ClinicHistoryResourcePlain toResourcePlainFromEntity(ClinicHistory entity) {
        return new ClinicHistoryResourcePlain(
                entity.getId(),
                entity.getMedicalHistoryNumber(),
                entity.getAge(),
                entity.getSex(),
                entity.getMainDiagnosis(),
                entity.getTreatment(),
                entity.getAnalysis(),
                entity.getStudentId()
        );
    }
}

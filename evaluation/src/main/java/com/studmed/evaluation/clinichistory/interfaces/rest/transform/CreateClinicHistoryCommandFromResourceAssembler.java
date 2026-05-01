package com.studmed.evaluation.clinichistory.interfaces.rest.transform;

import com.studmed.evaluation.clinichistory.domain.model.commands.CreateClinicHistoryCommand;
import com.studmed.evaluation.clinichistory.interfaces.rest.resource.CreateClinicHistoryResource;

public class CreateClinicHistoryCommandFromResourceAssembler {
    public static CreateClinicHistoryCommand toCommandFromResource(CreateClinicHistoryResource resource) {
        return new CreateClinicHistoryCommand(
                resource.medicalHistoryNumber(),
                resource.age(),
                resource.sex(),
                resource.mainDiagnosis(),
                resource.treatment(),
                resource.analysis(),
                resource.studentId()
        );
    }
}

package com.studmed.evaluation.interfaces.rest.transform;

import com.studmed.evaluation.domain.model.commands.UpdateEvaluationCommand;
import com.studmed.evaluation.interfaces.rest.resource.UpdateEvaluationResource;

public class UpdateEvaluationCommandFromResourceAssembler {
    public static UpdateEvaluationCommand toCommandFromResource(Long id, UpdateEvaluationResource resource) {
        return new UpdateEvaluationCommand(
                id,
                resource.name(),
                resource.description(),
                resource.price(),
                resource.imageUrl(),
                resource.rating(),
                resource.category());
    }
}
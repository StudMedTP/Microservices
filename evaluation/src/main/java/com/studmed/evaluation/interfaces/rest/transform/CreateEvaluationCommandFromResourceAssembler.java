package com.studmed.evaluation.interfaces.rest.transform;

import com.studmed.evaluation.domain.model.commands.CreateEvaluationCommand;
import com.studmed.evaluation.interfaces.rest.resource.CreateEvaluationResource;

public class CreateEvaluationCommandFromResourceAssembler {
    public static CreateEvaluationCommand toCommandFromResource(CreateEvaluationResource resource) {
        return new CreateEvaluationCommand(
                resource.name(),
                resource.description(),
                resource.price(),
                resource.imageUrl(),
                resource.rating(),
                resource.category());
    }
}
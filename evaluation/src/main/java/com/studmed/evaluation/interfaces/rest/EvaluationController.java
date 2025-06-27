package com.studmed.evaluation.interfaces.rest;

import com.studmed.evaluation.domain.model.commands.DeleteEvaluationCommand;
import com.studmed.evaluation.domain.model.queries.GetAllEvaluationQuery;
import com.studmed.evaluation.domain.model.queries.GetEvaluationByIdQuery;
import com.studmed.evaluation.domain.service.EvaluationCommandService;
import com.studmed.evaluation.domain.service.EvaluationQueryService;
import com.studmed.evaluation.interfaces.rest.resource.CreateEvaluationResource;
import com.studmed.evaluation.interfaces.rest.resource.EvaluationResource;
import com.studmed.evaluation.interfaces.rest.resource.UpdateEvaluationResource;
import com.studmed.evaluation.interfaces.rest.transform.CreateEvaluationCommandFromResourceAssembler;
import com.studmed.evaluation.interfaces.rest.transform.EvaluationResourceFromEntityAssembler;
import com.studmed.evaluation.interfaces.rest.transform.UpdateEvaluationCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/evaluations", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Evaluation", description = "Evaluation Management Endpoints")
public class EvaluationController {

    private final EvaluationCommandService evaluationCommandService;
    private final EvaluationQueryService evaluationQueryService;

    public EvaluationController(EvaluationCommandService evaluationCommandService, EvaluationQueryService evaluationQueryService) {
        this.evaluationCommandService = evaluationCommandService;
        this.evaluationQueryService = evaluationQueryService;
    }

    @PostMapping
    public ResponseEntity<EvaluationResource> createEvaluation(@RequestBody CreateEvaluationResource createEvaluationResource){

        var createEvaluationCommand = CreateEvaluationCommandFromResourceAssembler.toCommandFromResource(createEvaluationResource);
        var id = evaluationCommandService.handle(createEvaluationCommand);
        if (id == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getEvaluationByIdQuery = new GetEvaluationByIdQuery(id);
        var evaluation = evaluationQueryService.handle(getEvaluationByIdQuery);
        if (evaluation.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var evaluationResource = EvaluationResourceFromEntityAssembler.toResourceFromEntity(evaluation.get());
        return new ResponseEntity<>(evaluationResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EvaluationResource>> getAllEvaluations() {
        var getAllEvaluationQuery = new GetAllEvaluationQuery();
        var evaluation = evaluationQueryService.handle(getAllEvaluationQuery);
        var evaluationResource = evaluation.stream().map(EvaluationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(evaluationResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationResource> getEvaluationById(@PathVariable Long id) {
        var getEvaluationByIdQuery = new GetEvaluationByIdQuery(id);
        var evaluation = evaluationQueryService.handle(getEvaluationByIdQuery);
        if (evaluation.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var evaluationResource = EvaluationResourceFromEntityAssembler.toResourceFromEntity(evaluation.get());
        return ResponseEntity.ok(evaluationResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationResource> updateEvaluation(@PathVariable Long id, @RequestBody UpdateEvaluationResource updateEvaluationResource) {
        var updateEvaluationCommand = UpdateEvaluationCommandFromResourceAssembler.toCommandFromResource(id, updateEvaluationResource);
        var updateEvaluation = evaluationCommandService.handle(updateEvaluationCommand);
        if (updateEvaluation.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var evaluationResource = EvaluationResourceFromEntityAssembler.toResourceFromEntity(updateEvaluation.get());
        return ResponseEntity.ok(evaluationResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvaluation(@PathVariable Long id) {
        var deleteEvaluationCommand = new DeleteEvaluationCommand(id);
        evaluationCommandService.handle(deleteEvaluationCommand);
        return ResponseEntity.ok("Evaluation with given id successfully deleted");
    }
}
package com.studmed.user.coordinator.interfaces.rest;

import com.studmed.user.shared.exception.BadRequestException;
import com.studmed.user.coordinator.domain.model.aggregates.Coordinator;
import com.studmed.user.coordinator.domain.model.commands.CreateCoordinatorCommand;
import com.studmed.user.coordinator.domain.model.queries.GetCoordinatorByIdQuery;
import com.studmed.user.coordinator.domain.service.CoordinatorCommandService;
import com.studmed.user.coordinator.domain.service.CoordinatorQueryService;
import com.studmed.user.coordinator.interfaces.rest.resource.CreateCoordinatorResource;
import com.studmed.user.coordinator.interfaces.rest.resource.CoordinatorResource;
import com.studmed.user.coordinator.interfaces.rest.transform.CreateCoordinatorCommandFromResourceAssembler;
import com.studmed.user.coordinator.interfaces.rest.transform.CoordinatorResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/coordinators")
@Tag(name = "Coordinator", description = "Coordinator Management Endpoints")
public class CoordinatorController {

    private final CoordinatorCommandService coordinatorCommandService;
    private final CoordinatorQueryService coordinatorQueryService;

    public CoordinatorController(CoordinatorCommandService coordinatorCommandService, CoordinatorQueryService coordinatorQueryService) {
        this.coordinatorCommandService = coordinatorCommandService;
        this.coordinatorQueryService = coordinatorQueryService;
    }

    @PostMapping
    public ResponseEntity<CoordinatorResource> createCoordinator(@RequestBody @Valid CreateCoordinatorResource createCoordinatorResource) {
        CreateCoordinatorCommand createCoordinatorCommand = CreateCoordinatorCommandFromResourceAssembler.toCommandFromResource(createCoordinatorResource);
        Long id = coordinatorCommandService.handle(createCoordinatorCommand);

        Coordinator coordinator = coordinatorQueryService.handle(new GetCoordinatorByIdQuery(id));

        CoordinatorResource coordinatorResource = CoordinatorResourceFromEntityAssembler.toResourceFromEntity(coordinator);
        return ResponseEntity.status(HttpStatus.CREATED).body(coordinatorResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordinatorResource> getCoordinatorById(@PathVariable Long id) {
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        Coordinator coordinator = coordinatorQueryService.handle(new GetCoordinatorByIdQuery(id));

        CoordinatorResource coordinatorResource = CoordinatorResourceFromEntityAssembler.toResourceFromEntity(coordinator);
        return ResponseEntity.ok(coordinatorResource);
    }
}
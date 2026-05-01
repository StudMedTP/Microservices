package com.studmed.evaluation.clinichistory.interfaces.rest;

import com.studmed.evaluation.clinichistory.domain.model.aggregate.ClinicHistory;
import com.studmed.evaluation.clinichistory.domain.model.commands.CreateClinicHistoryCommand;
import com.studmed.evaluation.clinichistory.domain.model.queries.GetAllClinicHistoriesByUserIdQuery;
import com.studmed.evaluation.clinichistory.domain.model.queries.GetClinicHistoryByIdQuery;
import com.studmed.evaluation.clinichistory.domain.service.ClinicHistoryCommandService;
import com.studmed.evaluation.clinichistory.domain.service.ClinicHistoryQueryService;
import com.studmed.evaluation.clinichistory.interfaces.rest.resource.ClinicHistoryResource;
import com.studmed.evaluation.clinichistory.interfaces.rest.resource.ClinicHistoryResourcePlain;
import com.studmed.evaluation.clinichistory.interfaces.rest.resource.CreateClinicHistoryResource;
import com.studmed.evaluation.clinichistory.interfaces.rest.transform.CreateClinicHistoryCommandFromResourceAssembler;
import com.studmed.evaluation.clinichistory.interfaces.rest.transform.ClinicHistoryResourceFromEntityAssembler;
import com.studmed.evaluation.shared.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/clinic-histories")
@Tag(name = "ClinicHistory", description = "ClinicHistory Management Endpoints")
public class ClinicHistoryController {

    private final ClinicHistoryCommandService clinicHistoryCommandService;
    private final ClinicHistoryQueryService clinicHistoryQueryService;

    public ClinicHistoryController(ClinicHistoryCommandService clinicHistoryCommandService, ClinicHistoryQueryService clinicHistoryQueryService) {
        this.clinicHistoryCommandService = clinicHistoryCommandService;
        this.clinicHistoryQueryService = clinicHistoryQueryService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("ClinicHistory Microservice is up and running! 1.0");
    }

    @PostMapping
    public ResponseEntity<ClinicHistoryResourcePlain> createClinicHistory(@RequestBody @Valid CreateClinicHistoryResource resource) {
        CreateClinicHistoryCommand createClinicHistoryCommand = CreateClinicHistoryCommandFromResourceAssembler.toCommandFromResource(resource);
        Long id = clinicHistoryCommandService.handle(createClinicHistoryCommand);

        ClinicHistory clinicHistory = clinicHistoryQueryService.handle(new GetClinicHistoryByIdQuery(id));

        ClinicHistoryResourcePlain clinicHistoryResource = ClinicHistoryResourceFromEntityAssembler.toResourcePlainFromEntity(clinicHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body(clinicHistoryResource);
    }

    @GetMapping("/myObject")
    public ResponseEntity<Map<String, List<ClinicHistoryResource>>> getAllClinicHistoriesByToken(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<ClinicHistory> clinicHistories = clinicHistoryQueryService.handle(new GetAllClinicHistoriesByUserIdQuery(userDetails.id()));

        List<ClinicHistoryResource> clinicHistoryResources = clinicHistories.stream().map(ClinicHistoryResourceFromEntityAssembler::toResourceFromEntity).toList();

        Map<String, List<ClinicHistoryResource>> response = Map.of("clinic-histories", clinicHistoryResources);
        return ResponseEntity.ok(response);
    }
}

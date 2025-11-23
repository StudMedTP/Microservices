package com.studmed.user.medical_center.interfaces.rest;

import com.studmed.user.medical_center.domain.model.queries.GetAllMedicalCenters;
import com.studmed.user.shared.exception.BadRequestException;
import com.studmed.user.medical_center.domain.model.aggregates.MedicalCenter;
import com.studmed.user.medical_center.domain.model.commands.CreateMedicalCenterCommand;
import com.studmed.user.medical_center.domain.model.queries.GetMedicalCenterByIdQuery;
import com.studmed.user.medical_center.domain.service.MedicalCenterCommandService;
import com.studmed.user.medical_center.domain.service.MedicalCenterQueryService;
import com.studmed.user.medical_center.interfaces.rest.resource.CreateMedicalCenterResource;
import com.studmed.user.medical_center.interfaces.rest.resource.MedicalCenterResource;
import com.studmed.user.medical_center.interfaces.rest.transform.CreateMedicalCenterCommandFromResourceAssembler;
import com.studmed.user.medical_center.interfaces.rest.transform.MedicalCenterResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/medical-centers")
@Tag(name = "MedicalCenter", description = "MedicalCenter Management Endpoints")
public class MedicalCenterController {

    private final MedicalCenterCommandService medicalCenterCommandService;
    private final MedicalCenterQueryService medicalCenterQueryService;

    public MedicalCenterController(MedicalCenterCommandService medicalCenterCommandService, MedicalCenterQueryService medicalCenterQueryService) {
        this.medicalCenterCommandService = medicalCenterCommandService;
        this.medicalCenterQueryService = medicalCenterQueryService;
    }

    @PostMapping
    public ResponseEntity<MedicalCenterResource> createMedicalCenter(@RequestBody @Valid CreateMedicalCenterResource createMedicalCenterResource) {
        CreateMedicalCenterCommand createMedicalCenterCommand = CreateMedicalCenterCommandFromResourceAssembler.toCommandFromResource(createMedicalCenterResource);
        Long id = medicalCenterCommandService.handle(createMedicalCenterCommand);

        MedicalCenter medicalCenter = medicalCenterQueryService.handle(new GetMedicalCenterByIdQuery(id));

        MedicalCenterResource medicalCenterResource = MedicalCenterResourceFromEntityAssembler.toResourceFromEntity(medicalCenter);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalCenterResource);
    }

    @GetMapping
    public ResponseEntity<List<MedicalCenterResource>> getAllMedicalCenters() {
        List<MedicalCenter> medicalCenters = medicalCenterQueryService.handle(new GetAllMedicalCenters());

        List<MedicalCenterResource> medicalCenterResources = medicalCenters.stream().map(MedicalCenterResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(medicalCenterResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalCenterResource> getMedicalCenterById(@PathVariable Long id) {
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        MedicalCenter medicalCenter = medicalCenterQueryService.handle(new GetMedicalCenterByIdQuery(id));

        MedicalCenterResource medicalCenterResource = MedicalCenterResourceFromEntityAssembler.toResourceFromEntity(medicalCenter);
        return ResponseEntity.ok(medicalCenterResource);
    }
}
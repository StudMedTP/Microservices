package com.studmed.user.speciality.interfaces.rest;

import com.studmed.user.shared.exception.BadRequestException;
import com.studmed.user.speciality.domain.model.aggregates.Speciality;
import com.studmed.user.speciality.domain.model.commands.CreateSpecialityCommand;
import com.studmed.user.speciality.domain.model.queries.GetSpecialityByIdQuery;
import com.studmed.user.speciality.domain.service.SpecialityCommandService;
import com.studmed.user.speciality.domain.service.SpecialityQueryService;
import com.studmed.user.speciality.interfaces.rest.resource.CreateSpecialityResource;
import com.studmed.user.speciality.interfaces.rest.resource.SpecialityResource;
import com.studmed.user.speciality.interfaces.rest.transform.CreateSpecialityCommandFromResourceAssembler;
import com.studmed.user.speciality.interfaces.rest.transform.SpecialityResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/specialities")
@Tag(name = "Speciality", description = "Speciality Management Endpoints")
public class SpecialityController {

    private final SpecialityCommandService specialityCommandService;
    private final SpecialityQueryService specialityQueryService;

    public SpecialityController(SpecialityCommandService specialityCommandService, SpecialityQueryService specialityQueryService) {
        this.specialityCommandService = specialityCommandService;
        this.specialityQueryService = specialityQueryService;
    }

    @PostMapping
    public ResponseEntity<SpecialityResource> createSpeciality(@RequestBody @Valid CreateSpecialityResource createSpecialityResource) {
        CreateSpecialityCommand createSpecialityCommand = CreateSpecialityCommandFromResourceAssembler.toCommandFromResource(createSpecialityResource);
        Long id = specialityCommandService.handle(createSpecialityCommand);

        Speciality speciality = specialityQueryService.handle(new GetSpecialityByIdQuery(id));

        SpecialityResource specialityResource = SpecialityResourceFromEntityAssembler.toResourceFromEntity(speciality);
        return ResponseEntity.status(HttpStatus.CREATED).body(specialityResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialityResource> getSpecialityById(@PathVariable Long id) {
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        Speciality speciality = specialityQueryService.handle(new GetSpecialityByIdQuery(id));

        SpecialityResource specialityResource = SpecialityResourceFromEntityAssembler.toResourceFromEntity(speciality);
        return ResponseEntity.ok(specialityResource);
    }
}
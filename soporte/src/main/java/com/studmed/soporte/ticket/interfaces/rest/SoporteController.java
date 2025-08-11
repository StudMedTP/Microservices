package com.studmed.soporte.ticket.interfaces.rest;

import com.studmed.soporte.shared.exception.BadRequestException;
import com.studmed.soporte.ticket.domain.model.aggregates.Soporte;
import com.studmed.soporte.ticket.domain.model.commands.CreateSoporteCommand;
import com.studmed.soporte.ticket.domain.model.commands.DeleteSoporteCommand;
import com.studmed.soporte.ticket.domain.model.commands.UpdateSoporteCommand;
import com.studmed.soporte.ticket.domain.model.queries.GetAllSoporteQuery;
import com.studmed.soporte.ticket.domain.model.queries.GetSoporteByIdQuery;
import com.studmed.soporte.ticket.domain.service.SoporteCommandService;
import com.studmed.soporte.ticket.domain.service.SoporteQueryService;
import com.studmed.soporte.ticket.interfaces.rest.resource.CreateSoporteResource;
import com.studmed.soporte.ticket.interfaces.rest.resource.UpdateSoporteResource;
import com.studmed.soporte.ticket.interfaces.rest.transform.CreateSoporteCommandFromResourceAssembler;
import com.studmed.soporte.ticket.interfaces.rest.transform.SoporteResourceFromEntityAssembler;
import com.studmed.soporte.ticket.interfaces.rest.resource.SoporteResource;
import com.studmed.soporte.ticket.interfaces.rest.transform.UpdateSoporteCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/soportes")
@Tag(name = "Soporte", description = "Soporte Management Endpoints")
public class SoporteController {

    public final SoporteCommandService soporteCommandService;
    public final SoporteQueryService soporteQueryService;

    public SoporteController(SoporteCommandService soporteCommandService, SoporteQueryService soporteQueryService) {
        this.soporteCommandService = soporteCommandService;
        this.soporteQueryService = soporteQueryService;
    }

    @PostMapping
    public ResponseEntity<SoporteResource> createSoporte(@RequestBody @Valid CreateSoporteResource createSoporteResource) {
        CreateSoporteCommand createSoporteCommand = CreateSoporteCommandFromResourceAssembler.toCommandFromResource(createSoporteResource);
        Long id = soporteCommandService.handle(createSoporteCommand);

        Soporte soporte = soporteQueryService.handle(new GetSoporteByIdQuery(id));

        SoporteResource soporteResource = SoporteResourceFromEntityAssembler.toResourceFromEntity(soporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(soporteResource);
    }

    @GetMapping
    public ResponseEntity<List<SoporteResource>> getAllSoportes() {
        List<Soporte> soporte = soporteQueryService.handle(new GetAllSoporteQuery());

        List<SoporteResource> soporteResource = soporte.stream().map(SoporteResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(soporteResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoporteResource> getSoporteById(@PathVariable Long id) {
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        Soporte soporte = soporteQueryService.handle(new GetSoporteByIdQuery(id));

        SoporteResource soporteResource = SoporteResourceFromEntityAssembler.toResourceFromEntity(soporte);
        return ResponseEntity.ok(soporteResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoporteResource> updateSoporte(@PathVariable Long id, @RequestBody @Valid UpdateSoporteResource updateSoporteResource) {
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        UpdateSoporteCommand updateSoporteCommand = UpdateSoporteCommandFromResourceAssembler.toCommandFromResource(id, updateSoporteResource);
        Long soporteId = soporteCommandService.handle(updateSoporteCommand);

        Soporte soporte = soporteQueryService.handle(new GetSoporteByIdQuery(soporteId));

        SoporteResource soporteResource = SoporteResourceFromEntityAssembler.toResourceFromEntity(soporte);
        return ResponseEntity.ok(soporteResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSoporte(@PathVariable Long id) {
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        DeleteSoporteCommand deleteSoporteCommand = new DeleteSoporteCommand(id);
        soporteCommandService.handle(deleteSoporteCommand);

        return ResponseEntity.noContent().build();
    }
}
package com.studmed.soporte.interfaces.rest;

import com.studmed.soporte.domain.model.commands.DeleteSoporteCommand;
import com.studmed.soporte.domain.model.queries.GetAllSoporteQuery;
import com.studmed.soporte.domain.model.queries.GetSoporteByIdQuery;
import com.studmed.soporte.domain.service.SoporteCommandService;
import com.studmed.soporte.domain.service.SoporteQueryService;
import com.studmed.soporte.interfaces.rest.resource.CreateSoporteResource;
import com.studmed.soporte.interfaces.rest.resource.UpdateSoporteResource;
import com.studmed.soporte.interfaces.rest.transform.CreateSoporteCommandFromResourceAssembler;
import com.studmed.soporte.interfaces.rest.transform.SoporteResourceFromEntityAssembler;
import com.studmed.soporte.interfaces.rest.resource.SoporteResource;
import com.studmed.soporte.interfaces.rest.transform.UpdateSoporteCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/soportes", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Soporte", description = "Soporte Management Endpoints")
public class SoporteController {

    public final SoporteCommandService soporteCommandService;
    public final SoporteQueryService soporteQueryService;

    public SoporteController(SoporteCommandService soporteCommandService, SoporteQueryService soporteQueryService) {
        this.soporteCommandService = soporteCommandService;
        this.soporteQueryService = soporteQueryService;
    }

    @PostMapping
    public ResponseEntity<SoporteResource> createSoporte(@RequestBody CreateSoporteResource createSoporteResource) {

        var createSoporteCommand = CreateSoporteCommandFromResourceAssembler.toCommandFromResource(createSoporteResource);
        var id = soporteCommandService.handle(createSoporteCommand);
        if (id == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getSoporteByIdQuery = new GetSoporteByIdQuery(id);
        var soporte = soporteQueryService.handle(getSoporteByIdQuery);
        if (soporte.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var soporteResource = SoporteResourceFromEntityAssembler.toResourceFromEntity(soporte.get());
        return new ResponseEntity<>(soporteResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SoporteResource>> getAllSoportes() {
        var getAllSoporteQuery = new GetAllSoporteQuery();
        var soporte = soporteQueryService.handle(getAllSoporteQuery);
        var soporteResource = soporte.stream().map(SoporteResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(soporteResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoporteResource> getSoporteById(@PathVariable Long id) {
        var getSoporteByIdQuery = new GetSoporteByIdQuery(id);
        var soporte = soporteQueryService.handle(getSoporteByIdQuery);
        if (soporte.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var soporteResource = SoporteResourceFromEntityAssembler.toResourceFromEntity(soporte.get());
        return ResponseEntity.ok(soporteResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoporteResource> updateSoporte(@PathVariable Long id, @RequestBody UpdateSoporteResource updateSoporteResource) {
        var updateSoporteCommand = UpdateSoporteCommandFromResourceAssembler.toCommandFromResource(id, updateSoporteResource);
        var updatedSoporte = soporteCommandService.handle(updateSoporteCommand);
        if (updatedSoporte.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var soporteResource = SoporteResourceFromEntityAssembler.toResourceFromEntity(updatedSoporte.get());
        return ResponseEntity.ok(soporteResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSoporte(@PathVariable Long id) {
        var deleteSoporteCommand = new DeleteSoporteCommand(id);
        soporteCommandService.handle(deleteSoporteCommand);
        return ResponseEntity.ok("Soporte with given id successfully deleted");
    }
}
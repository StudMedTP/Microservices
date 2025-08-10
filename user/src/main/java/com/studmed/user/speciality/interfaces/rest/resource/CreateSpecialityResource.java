package com.studmed.user.speciality.interfaces.rest.resource;

import jakarta.validation.constraints.NotBlank;

public record CreateSpecialityResource(
        @NotBlank()
        String name) {}
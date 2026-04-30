package com.studmed.user.medical_center.domain.model.commands;

public record CreateMedicalCenterCommand(String name,
                                         Double latitude,
                                         Double longitude) {}
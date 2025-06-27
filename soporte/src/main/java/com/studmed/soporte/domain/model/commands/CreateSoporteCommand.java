package com.studmed.soporte.domain.model.commands;

public record CreateSoporteCommand(String origin,
                                   String destination,
                                   String date){
}
package com.studmed.soporte.domain.model.commands;

public record CreateSoporteCommand(String ticketCreationTime,
                                   String ticketSentTitle,
                                   String ticketSentMessage,
                                   String ticketState,
                                   String ticketResponseTitle,
                                   String ticketResponseMessage,
                                   String ticketResponseTime){
}
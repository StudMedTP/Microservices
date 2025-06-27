package com.studmed.soporte.interfaces.rest.resource;

public record CreateSoporteResource(String ticketCreationTime,
                                    String ticketSentTitle,
                                    String ticketSentMessage,
                                    String ticketState,
                                    String ticketResponseTitle,
                                    String ticketResponseMessage,
                                    String ticketResponseTime){
}

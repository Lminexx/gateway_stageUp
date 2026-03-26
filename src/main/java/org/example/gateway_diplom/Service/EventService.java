package org.example.gateway_diplom.Service;

import org.springframework.data.domain.Page;
import org.example.gateway_diplom.Client.EventClient;
import org.example.gateway_diplom.DTO.eventDTO.EventDTO;
import org.example.gateway_diplom.DTO.eventDTO.EventRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventService {

    private final EventClient eventClient;

    public EventService(EventClient eventClient) {
        this.eventClient = eventClient;
    }

    public EventDTO createEvent(EventRequest eventRequest) {
        return eventClient.createEvent(eventRequest);
    }

    public Page<EventDTO> getEvents(int page, int size, String sortBy, String direction) {
        return eventClient.getEvents(page, size, sortBy, direction);
    }

    public Page<EventDTO> getMyEvents(int page, int size, String sortBy, String direction, UUID organizedUserId) {
        return eventClient.getMyEvents(page, size, sortBy, direction, organizedUserId);
    }

    public EventDTO getEvent(UUID eventId, UUID userId) {
        return eventClient.getEvent(eventId, userId);
    }

    public EventDTO updateEvent(UUID eventId,UUID organizerUserId, EventRequest eventRequest) {
        return eventClient.updateEvent(eventId, organizerUserId, eventRequest);
    }

    public void deleteEvent(UUID eventId, UUID userId) {
        eventClient.deleteEvent(eventId, userId);
    }
}

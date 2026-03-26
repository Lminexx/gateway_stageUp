package org.example.gateway_diplom.Controller;

import org.springframework.data.domain.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.gateway_diplom.DTO.eventDTO.EventDTO;
import org.example.gateway_diplom.DTO.eventDTO.EventRequest;
import org.example.gateway_diplom.Service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventRequest eventRequest,
                                                @RequestHeader(value = "X-User-Id", required = false) UUID organizerUserId){
        log.info("Create event {}", eventRequest);
        eventRequest.setOrganizerUserId(organizerUserId);
        return ResponseEntity.ok(eventService.createEvent(eventRequest));
    }

    @GetMapping
    public ResponseEntity<Page<EventDTO>> getEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "eventDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        log.info("get events");

        return ResponseEntity.ok(eventService.getEvents(page, size, sortBy, direction));
    }

    @GetMapping("/myEvents")
    public ResponseEntity<Page<EventDTO>> getMyEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "eventDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestHeader(value = "X-User-Id", required = false) UUID organizerUserId) {
        log.info("get my events");

        return ResponseEntity.ok(eventService.getMyEvents(page, size, sortBy, direction, organizerUserId));
    }


    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable UUID eventId,
                                             @RequestHeader(value = "X-User-Id", required = false) UUID userId) {
        log.info("get event {}", eventId);
        return ResponseEntity.ok(eventService.getEvent(eventId, userId));
    }
    @PutMapping("/{eventId}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable UUID eventId,
                                                @RequestHeader("X-User-Id") UUID organizerUserId,
                                                @RequestBody EventRequest request){
        log.info("update event {}", eventId);
        return ResponseEntity.ok(eventService.updateEvent(eventId, organizerUserId, request));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID eventId,
                                            @RequestHeader(value = "X-User-Id", required = true) UUID userId) {
        log.info("delete event {}", eventId);
        eventService.deleteEvent(eventId, userId);
        return ResponseEntity.noContent().build();
    }
}

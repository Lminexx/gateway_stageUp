package org.example.gateway_diplom.Client;

import org.springframework.data.domain.Page;
import org.example.gateway_diplom.DTO.eventDTO.EventDTO;
import org.example.gateway_diplom.DTO.eventDTO.EventRequest;
import org.example.gateway_diplom.Filter.RestResponsePage;
import org.example.gateway_diplom.Properties.webclient.EventClientProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.UUID;

@Component
public class EventClient {

    private final EventClientProperties eventClientProperties;
    private final RestTemplate restTemplate;

    public EventClient(EventClientProperties eventClientProperties, RestTemplate restTemplate) {
        this.eventClientProperties = eventClientProperties;
        this.restTemplate = restTemplate;
    }


    public EventDTO createEvent(EventRequest eventRequest) {
        return restTemplate.postForEntity(
                eventClientProperties.getBaseUrl() + eventClientProperties.getResources().getEventCreate(),
                eventRequest,
                EventDTO.class
        ).getBody();
    }


    public Page<EventDTO> getEvents(int page, int size, String sortBy, String direction) {
        String url = eventClientProperties.getBaseUrl() + eventClientProperties.getResources().getEventGets() +
                "?page=" + page + "&size=" + size +
                "&sortBy=" + sortBy + "&direction=" + direction;

        ParameterizedTypeReference<RestResponsePage<EventDTO>> type =
                new ParameterizedTypeReference<RestResponsePage<EventDTO>>() {};

        return restTemplate.exchange(url, HttpMethod.GET, null, type).getBody();
    }

    public Page<EventDTO> getMyEvents(int page, int size, String sortBy, String direction, UUID organizerUserId) {
        String url = eventClientProperties.getBaseUrl() + eventClientProperties.getResources().getGetMyEvents() +
                "?page=" + page + "&size=" + size +
                "&sortBy=" + sortBy + "&direction=" + direction + "&organizerUserId=" + organizerUserId;

        ParameterizedTypeReference<RestResponsePage<EventDTO>> type =
                new ParameterizedTypeReference<RestResponsePage<EventDTO>>() {};

        return restTemplate.exchange(url, HttpMethod.GET, null, type).getBody();
    }

    public EventDTO getEvent(UUID eventId, UUID userId) {
        HttpHeaders headers = new HttpHeaders();
        if (userId != null) {
            headers.set("X-User-Id", userId.toString());
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                eventClientProperties.getBaseUrl() + eventClientProperties.getResources().getEventGet(),
                HttpMethod.GET,
                entity,
                EventDTO.class,
                eventId
        ).getBody();
    }

    public EventDTO updateEvent(UUID eventId, UUID organizerUserId, EventRequest eventRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (organizerUserId != null) {
            headers.set("X-User-Id", organizerUserId.toString());
        }

        HttpEntity<EventRequest> entity = new HttpEntity<>(eventRequest, headers);

        return restTemplate.exchange(
                eventClientProperties.getBaseUrl() + eventClientProperties.getResources().getUpdateEvent(),
                HttpMethod.PUT,
                entity,
                EventDTO.class,
                eventId
        ).getBody();
    }

    public void deleteEvent(UUID eventId, UUID userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (userId != null) {
            headers.set("X-User-Id", userId.toString());
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(
                eventClientProperties.getBaseUrl() + eventClientProperties.getResources().getDeleteEvent(),
                HttpMethod.DELETE,
                entity,
                Void.class,
                eventId
        ).getBody();
    }
}

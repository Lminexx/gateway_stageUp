package org.example.gateway_diplom.Client;
import org.example.gateway_diplom.DTO.applicationDTO.ApplicationCreateRequest;
import org.example.gateway_diplom.DTO.applicationDTO.ApplicationDTO;
import org.example.gateway_diplom.DTO.applicationDTO.ApplicationStatusUpdateRequest;
import org.example.gateway_diplom.Filter.RestResponsePage;
import org.example.gateway_diplom.Properties.webclient.ApplicationClientProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ApplicationClient {

    private final ApplicationClientProperties applicationClientProperties;
    private final RestTemplate restTemplate;

    public ApplicationClient(ApplicationClientProperties applicationClientProperties, RestTemplate restTemplate) {
        this.applicationClientProperties = applicationClientProperties;
        this.restTemplate = restTemplate;
    }

    public ApplicationDTO createApplication(ApplicationCreateRequest applicationCreateRequest) {
        return restTemplate.postForEntity(
                applicationClientProperties.getBaseUrl() + applicationClientProperties.getResources().getApplicationCreate(),
                applicationCreateRequest,
                ApplicationDTO.class
        ).getBody();
    }

    public ApplicationDTO updateApplication(UUID id, ApplicationStatusUpdateRequest applicationStatusUpdateRequest) {
        return restTemplate.patchForObject(
                applicationClientProperties.getBaseUrl() + applicationClientProperties.getResources().getApplicationUpdateStatus(),
                applicationStatusUpdateRequest,
                ApplicationDTO.class,
                id
        );
    }

    public Page<ApplicationDTO> getApplications(UUID eventId, int page, int size) {
        String url = applicationClientProperties.getBaseUrl()
                + applicationClientProperties.getResources().getApplicationGet()
                + "/{eventId}?page={page}&size={size}";

        ParameterizedTypeReference<RestResponsePage<ApplicationDTO>> type =
                new ParameterizedTypeReference<RestResponsePage<ApplicationDTO>>() {};

        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("eventId", eventId);
        uriVariables.put("page", page);
        uriVariables.put("size", size);

        // Передаем map в конце
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                type,
                uriVariables
        ).getBody();
    }
}

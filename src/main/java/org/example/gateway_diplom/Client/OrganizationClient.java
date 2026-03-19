package org.example.gateway_diplom.Client;

import org.example.gateway_diplom.DTO.profilesDTO.ArtistDTO;
import org.example.gateway_diplom.DTO.profilesDTO.ArtistRequest;
import org.example.gateway_diplom.DTO.profilesDTO.OrganizationDTO;
import org.example.gateway_diplom.DTO.profilesDTO.OrganizationRequest;
import org.example.gateway_diplom.Properties.webclient.OrganizationClientProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class OrganizationClient {

    private final OrganizationClientProperties organizationClientProperties;
    private final RestTemplate restTemplate;

    public OrganizationClient(OrganizationClientProperties organizationClientProperties, RestTemplate restTemplate) {
        this.organizationClientProperties = organizationClientProperties;
        this.restTemplate = restTemplate;
    }

    public OrganizationDTO createOrg(OrganizationRequest orgRequest) {
        return restTemplate.postForEntity(
                organizationClientProperties.getBaseUrl() + organizationClientProperties.getResources().getOrganizationCreate(),
                orgRequest,
                OrganizationDTO.class
        ).getBody();
    }

    public OrganizationDTO getOrganizationById(UUID userId) {
        return restTemplate.getForObject(
                organizationClientProperties.getBaseUrl() + organizationClientProperties.getResources().getOrganizationGet(),
                OrganizationDTO.class,
                userId
        );
    }

    public OrganizationDTO updateOrganization(UUID userId, OrganizationRequest orgRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OrganizationRequest> entity = new HttpEntity<>(orgRequest, headers);

        return restTemplate.exchange(
                organizationClientProperties.getBaseUrl() +
                        organizationClientProperties.getResources().getOrganizationUpdate(),
                HttpMethod.PUT,
                entity,
                OrganizationDTO.class,
                userId
        ).getBody();
    }
}

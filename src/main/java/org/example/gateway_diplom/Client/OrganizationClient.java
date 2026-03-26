package org.example.gateway_diplom.Client;

import org.example.gateway_diplom.DTO.AvatarDTO;
import org.example.gateway_diplom.DTO.profilesDTO.OrganizationDTO;
import org.example.gateway_diplom.DTO.profilesDTO.OrganizationRequest;
import org.example.gateway_diplom.Properties.webclient.OrganizationClientProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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


    public AvatarDTO uploadAvatar(MultipartFile file, UUID userId){
        String url = organizationClientProperties.getBaseUrl() +
                organizationClientProperties.getResources().getOrganizationUploadAvatar();

        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .buildAndExpand(userId)
                .toUri();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("avatarFile", file.getResource());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(
                uri,
                HttpMethod.POST,
                entity,
                AvatarDTO.class
        ).getBody();
    }

    public AvatarDTO getAvatar(UUID userId) {
        return restTemplate.getForObject(
                organizationClientProperties.getBaseUrl() + organizationClientProperties.getResources().getOrganizationGetAvatar(),
                AvatarDTO.class,
                userId
        );
    }
}

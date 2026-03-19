package org.example.gateway_diplom.Service;

import org.example.gateway_diplom.Client.OrganizationClient;
import org.example.gateway_diplom.DTO.profilesDTO.OrganizationDTO;
import org.example.gateway_diplom.DTO.profilesDTO.OrganizationRequest;
import org.example.gateway_diplom.Properties.webclient.OrganizationClientProperties;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizationService {


    private final OrganizationClient organizationClient;

    public OrganizationService(OrganizationClient organizationClient) {
        this.organizationClient = organizationClient;
    }

    public OrganizationDTO createOrganization(OrganizationRequest organizationRequest) {
        return organizationClient.createOrg(organizationRequest);
    }

    public OrganizationDTO getOrganization(UUID userId) {
        return organizationClient.getOrganizationById(userId);
    }

    public OrganizationDTO updateOrganization(UUID userId, OrganizationRequest organizationRequest) {
        return organizationClient.updateOrganization(userId, organizationRequest);
    }
}

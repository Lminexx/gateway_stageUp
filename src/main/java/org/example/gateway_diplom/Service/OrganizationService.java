package org.example.gateway_diplom.Service;

import org.example.gateway_diplom.Client.OrganizationClient;
import org.example.gateway_diplom.DTO.AvatarDTO;
import org.example.gateway_diplom.DTO.profilesDTO.OrganizationDTO;
import org.example.gateway_diplom.DTO.profilesDTO.OrganizationRequest;
import org.example.gateway_diplom.Properties.webclient.MinioClientProperties;
import org.example.gateway_diplom.Properties.webclient.OrganizationClientProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class OrganizationService {


    private final OrganizationClient organizationClient;
    private final MinioClientProperties minioClientProperties;

    public OrganizationService(OrganizationClient organizationClient, MinioClientProperties minioClientProperties) {
        this.organizationClient = organizationClient;
        this.minioClientProperties = minioClientProperties;
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

    public AvatarDTO uploadAvatar(MultipartFile multipartFile, UUID userId) {
        return organizationClient.uploadAvatar(multipartFile, userId);
    }

    public AvatarDTO getAvatar(UUID userId) {
        AvatarDTO avatarDTO = organizationClient.getAvatar(userId);
        avatarDTO.setAvatarUrl(minioClientProperties.getBaseUrl() +
                minioClientProperties.getResources().getOrganizer()
                + "/"
                + avatarDTO.getAvatarUrl());
        return avatarDTO;
    }
}

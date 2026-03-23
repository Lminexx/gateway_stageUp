package org.example.gateway_diplom.Service;

import org.example.gateway_diplom.Client.ArtistClient;
import org.example.gateway_diplom.DTO.AvatarDTO;
import org.example.gateway_diplom.DTO.profilesDTO.ArtistDTO;
import org.example.gateway_diplom.DTO.profilesDTO.ArtistDisplayNameDTO;
import org.example.gateway_diplom.DTO.profilesDTO.ArtistRequest;
import org.example.gateway_diplom.Properties.webclient.MinioClientProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class ArtistService {

    private final ArtistClient artistClient;
    private final MinioClientProperties minioClientProperties;

    public ArtistService(ArtistClient artistClient, MinioClientProperties minioClientProperties) {
        this.artistClient = artistClient;
        this.minioClientProperties = minioClientProperties;
    }

    public ArtistDTO createArtist(ArtistRequest artistRequest) {
        return artistClient.createArtist(artistRequest);
    }

    public ArtistDTO getArtistById(UUID userId) {
        return artistClient.getArtistById(userId);
    }

    public ArtistDTO editArtist(UUID userId, ArtistRequest artistRequest) {
        return artistClient.updateArtist(userId, artistRequest);
    }

    public ArtistDisplayNameDTO getArtistDisplayName(UUID userId) {
        return artistClient.getArtistDisplayName(userId);
    }

    public AvatarDTO uploadAvatar(MultipartFile multipartFile, UUID userId) {
        return artistClient.uploadAvatar(multipartFile, userId);
    }

    public AvatarDTO getAvatar(UUID userId) {
        AvatarDTO avatarDTO = artistClient.getAvatar(userId);
        avatarDTO.setAvatarUrl(minioClientProperties.getBaseUrl() +
                minioClientProperties.getResources().getArtist()
                + "/"
                + avatarDTO.getAvatarUrl());
        return avatarDTO;
    }
}

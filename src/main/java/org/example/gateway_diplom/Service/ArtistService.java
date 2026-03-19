package org.example.gateway_diplom.Service;

import org.example.gateway_diplom.Client.ArtistClient;
import org.example.gateway_diplom.DTO.profilesDTO.ArtistDTO;
import org.example.gateway_diplom.DTO.profilesDTO.ArtistDisplayNameDTO;
import org.example.gateway_diplom.DTO.profilesDTO.ArtistRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArtistService {

    private final ArtistClient artistClient;

    public ArtistService(ArtistClient artistClient) {
        this.artistClient = artistClient;
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
}

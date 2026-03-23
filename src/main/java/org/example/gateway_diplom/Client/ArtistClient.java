package org.example.gateway_diplom.Client;

import org.example.gateway_diplom.DTO.AvatarDTO;
import org.example.gateway_diplom.DTO.profilesDTO.ArtistDTO;
import org.example.gateway_diplom.DTO.profilesDTO.ArtistDisplayNameDTO;
import org.example.gateway_diplom.DTO.profilesDTO.ArtistRequest;
import org.example.gateway_diplom.Properties.webclient.ArtistClientProperties;
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
public class ArtistClient {

    private final ArtistClientProperties artistClientProperties;
    private final RestTemplate restTemplate;

    public ArtistClient(ArtistClientProperties artistClientProperties, RestTemplate restTemplate) {
        this.artistClientProperties = artistClientProperties;
        this.restTemplate = restTemplate;
    }

    public ArtistDTO createArtist(ArtistRequest artRequest) {
        return restTemplate.postForEntity(
                artistClientProperties.getBaseUrl() + artistClientProperties.getResources().getArtistCreate(),
                artRequest,
                ArtistDTO.class
        ).getBody();
    }

    public ArtistDTO getArtistById(UUID userId) {
        return restTemplate.getForObject(
                artistClientProperties.getBaseUrl() + artistClientProperties.getResources().getArtistGet(),
                ArtistDTO.class,
                userId
        );
    }

    public ArtistDTO updateArtist(UUID userId, ArtistRequest artRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ArtistRequest> entity = new HttpEntity<>(artRequest, headers);

        return restTemplate.exchange(
                artistClientProperties.getBaseUrl() +
                        artistClientProperties.getResources().getArtistUpdate(),
                HttpMethod.PUT,
                entity,
                ArtistDTO.class,
                userId
        ).getBody();
    }

    public ArtistDisplayNameDTO getArtistDisplayName(UUID userId) {
        return restTemplate.getForObject(
                artistClientProperties.getBaseUrl() + artistClientProperties.getResources().getArtistName(),
                ArtistDisplayNameDTO.class,
                userId
        );
    }

    public AvatarDTO uploadAvatar(MultipartFile file, UUID userId){
        String url = artistClientProperties.getBaseUrl() +
                artistClientProperties.getResources().getArtistUploadAvatar();

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
                artistClientProperties.getBaseUrl() + artistClientProperties.getResources().getArtistGetAvatar(),
                AvatarDTO.class,
                userId
        );
    }
}

package org.example.gateway_diplom.Controller;

import lombok.extern.slf4j.Slf4j;
import org.example.gateway_diplom.DTO.profilesDTO.ArtistDTO;
import org.example.gateway_diplom.DTO.profilesDTO.ArtistRequest;
import org.example.gateway_diplom.Service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping("/create")
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistRequest artistRequest) {
        log.info("create Artist {} ", artistRequest);
        return ResponseEntity.ok(artistService.createArtist(artistRequest));
    }

    @GetMapping("/profile")
    public ResponseEntity<ArtistDTO> getArtistProfile(
            @RequestHeader("X-User-Id") UUID userId) {
        log.info("get Artist {} ", userId);
        return new ResponseEntity<>(artistService.getArtistById(userId), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<ArtistDTO> editArtistProfile(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody ArtistRequest artistRequest) {
        log.info("edit Artist {} ", userId);
        return new ResponseEntity<>(artistService.editArtist(userId,artistRequest), HttpStatus.OK);
    }


}

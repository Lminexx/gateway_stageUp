package org.example.gateway_diplom.Controller;
import lombok.extern.slf4j.Slf4j;
import org.example.gateway_diplom.DTO.applicationDTO.ApplicationCreateRequest;
import org.example.gateway_diplom.DTO.applicationDTO.ApplicationDTO;
import org.example.gateway_diplom.DTO.applicationDTO.ApplicationStatusUpdateRequest;
import org.example.gateway_diplom.Service.ApplicationService;
import org.example.gateway_diplom.Service.ArtistService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/application")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ArtistService artistService;

    public ApplicationController(ApplicationService applicationService, ArtistService artistService) {
        this.applicationService = applicationService;
        this.artistService = artistService;
    }
    @PostMapping("/create")
    public ResponseEntity<ApplicationDTO> createApplication(@RequestBody ApplicationCreateRequest applicationCreateRequest,
                                                            @RequestHeader("X-User-Id") UUID userId) {
        applicationCreateRequest.setArtistUserId(userId);
        log.info("Creating application {}", applicationCreateRequest);
        return ResponseEntity.ok(applicationService.createApplication(applicationCreateRequest));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationDTO> updateApplicationStatus(
            @PathVariable("id") UUID id,
            @RequestBody ApplicationStatusUpdateRequest updateRequest) {
        log.info("Updating application status with id {} {}", id, updateRequest);
        return ResponseEntity.ok(applicationService.updateStatusApplication(id, updateRequest));
    }
    @GetMapping("/event/{eventId}")
    public ResponseEntity<Page<ApplicationDTO>> getApplications(@PathVariable UUID eventId,
                                                                @RequestHeader("X-User-Id") UUID artistUserId,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "20") int size){
        log.info("get Application {}", eventId);
        Page<ApplicationDTO> apps = applicationService.getApplication(eventId, page, size);
        for (ApplicationDTO applicationDTO : apps.getContent()) {
            String artistName = artistService.getArtistDisplayName(applicationDTO.getArtistUserId()).getArtistName();
            applicationDTO.setArtistName(artistName);
        }
        return ResponseEntity.ok(apps);
    }
}

package org.example.gateway_diplom.Controller;
import lombok.extern.slf4j.Slf4j;
import org.example.gateway_diplom.DTO.AvatarDTO;
import org.example.gateway_diplom.DTO.profilesDTO.OrganizationDTO;
import org.example.gateway_diplom.DTO.profilesDTO.OrganizationRequest;
import org.example.gateway_diplom.Service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/organization")
public class OrganizationController {


    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/create")
    public ResponseEntity<OrganizationDTO> createOrganization(@RequestBody OrganizationRequest organizationRequest) {
        log.info("Create Organization {}", organizationRequest);
        return ResponseEntity.ok(organizationService.createOrganization(organizationRequest));
    }

    @GetMapping("/profile")
    public ResponseEntity<OrganizationDTO> getOrganization(
            @RequestHeader("X-User-Id") UUID userId){
        log.info("Get Organization {}", userId);
        return ResponseEntity.ok(organizationService.getOrganization(userId));
    }

    @PutMapping("/profile")
    public ResponseEntity<OrganizationDTO> updateOrganization(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody OrganizationRequest organizationRequest){
        log.info("Update Organization {}", organizationRequest);
        return ResponseEntity.ok(organizationService.updateOrganization(userId, organizationRequest));
    }

    @PostMapping("/uploadAvatar")
    public ResponseEntity<AvatarDTO> uploadAvatar(@RequestParam("file") MultipartFile file,
                                                  @RequestHeader("X-User-Id") UUID userId) {
        log.info("upload avatar {} ", file);
        return new ResponseEntity<>(organizationService.uploadAvatar(file,userId), HttpStatus.OK);
    }

    @GetMapping("/avatar/{userId}")
    public ResponseEntity<AvatarDTO> getAvatar(@PathVariable UUID userId){
        log.info("get avatar for organization: {} ", userId);
        return ResponseEntity.ok(organizationService.getAvatar(userId));
    }

}

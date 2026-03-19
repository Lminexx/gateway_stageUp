package org.example.gateway_diplom.DTO.applicationDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {

    private UUID id;
    private UUID eventId;
    private UUID artistUserId;
    private String artistName;
    private Instant applicationDate;
    private ApplicationStatus status;
    private String coverLetter;
}

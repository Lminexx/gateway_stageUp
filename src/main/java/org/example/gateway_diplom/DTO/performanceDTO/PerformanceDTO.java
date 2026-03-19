package org.example.gateway_diplom.DTO.performanceDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceDTO {

    private UUID id;
    private UUID eventId;
    private UUID applicationId;
    private UUID artistUserId;
    private LocalDate performanceDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal actualFeePaid;
    private PerformanceStatus status;
    private Boolean feedbackRequested;
}

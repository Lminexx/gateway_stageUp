package org.example.gateway_diplom.Controller;

import lombok.extern.slf4j.Slf4j;
import org.example.gateway_diplom.DTO.performanceDTO.PerformanceCreateRequest;
import org.example.gateway_diplom.DTO.performanceDTO.PerformanceDTO;
import org.example.gateway_diplom.DTO.performanceDTO.PerformanceStatusUpdateRequest;
import org.example.gateway_diplom.Service.PerformanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }
    @PostMapping("/create")
    public ResponseEntity<PerformanceDTO> createPerformance(@RequestBody PerformanceCreateRequest performanceCreateRequest) {
        log.info("create performance {}", performanceCreateRequest);
        return ResponseEntity.ok(performanceService.createPerformance(performanceCreateRequest));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<PerformanceDTO> updatePerformanceStatus(@PathVariable("id") UUID id,
                                                                  @RequestBody PerformanceStatusUpdateRequest performanceStatusUpdateRequest) {
        log.info("update performance id {}", id);
        return ResponseEntity.ok(performanceService.updatePerformanceStatus(id, performanceStatusUpdateRequest));
    }
}

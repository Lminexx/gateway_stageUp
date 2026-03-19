package org.example.gateway_diplom.Service;

import org.example.gateway_diplom.Client.PerformanceClient;
import org.example.gateway_diplom.DTO.performanceDTO.PerformanceCreateRequest;
import org.example.gateway_diplom.DTO.performanceDTO.PerformanceDTO;
import org.example.gateway_diplom.DTO.performanceDTO.PerformanceStatusUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PerformanceService {

    private final PerformanceClient performanceClient;

    public PerformanceService(PerformanceClient performanceClient) {
        this.performanceClient = performanceClient;
    }

    public PerformanceDTO createPerformance(PerformanceCreateRequest performanceCreateRequest) {
        return performanceClient.createPerformance(performanceCreateRequest);
    }

    public PerformanceDTO updatePerformanceStatus(UUID id, PerformanceStatusUpdateRequest performanceStatusUpdateRequest) {
        return performanceClient.updatePerformanceStatus(id, performanceStatusUpdateRequest);
    }
}

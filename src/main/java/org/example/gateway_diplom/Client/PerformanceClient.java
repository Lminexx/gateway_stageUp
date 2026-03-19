package org.example.gateway_diplom.Client;

import org.example.gateway_diplom.DTO.performanceDTO.PerformanceCreateRequest;
import org.example.gateway_diplom.DTO.performanceDTO.PerformanceDTO;
import org.example.gateway_diplom.DTO.performanceDTO.PerformanceStatusUpdateRequest;
import org.example.gateway_diplom.Properties.webclient.PerformanceClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class PerformanceClient {

    private final PerformanceClientProperties performanceClientProperties;
    private final RestTemplate restTemplate;

    public PerformanceClient(PerformanceClientProperties performanceClientProperties, RestTemplate restTemplate) {
        this.performanceClientProperties = performanceClientProperties;
        this.restTemplate = restTemplate;
    }

    public PerformanceDTO createPerformance(PerformanceCreateRequest performanceCreateRequest) {
        return restTemplate.postForEntity(
                performanceClientProperties.getBaseUrl() + performanceClientProperties.getResources().getPerformanceCreate(),
                performanceCreateRequest,
                PerformanceDTO.class
        ).getBody();
    }

    public PerformanceDTO updatePerformanceStatus(UUID id, PerformanceStatusUpdateRequest performanceStatusUpdateRequest) {
        return restTemplate.patchForObject(
                performanceClientProperties.getBaseUrl() + performanceClientProperties.getResources().getPerformanceUpdateStatus(),
                performanceStatusUpdateRequest,
                PerformanceDTO.class,
                id
        );
    }
}

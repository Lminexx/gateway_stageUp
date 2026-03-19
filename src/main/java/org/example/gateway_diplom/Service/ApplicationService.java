package org.example.gateway_diplom.Service;

import org.example.gateway_diplom.Client.ApplicationClient;
import org.example.gateway_diplom.DTO.applicationDTO.ApplicationCreateRequest;
import org.example.gateway_diplom.DTO.applicationDTO.ApplicationDTO;
import org.example.gateway_diplom.DTO.applicationDTO.ApplicationStatusUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplicationService {

    private final ApplicationClient applicationClient;

    public ApplicationService(ApplicationClient applicationClient) {
        this.applicationClient = applicationClient;
    }


    public ApplicationDTO createApplication(ApplicationCreateRequest applicationCreateRequest) {
        return applicationClient.createApplication(applicationCreateRequest);
    }

    public ApplicationDTO updateStatusApplication(UUID id, ApplicationStatusUpdateRequest applicationStatusUpdateRequest) {
        return applicationClient.updateApplication(id, applicationStatusUpdateRequest);
    }

    public Page<ApplicationDTO> getApplication(UUID eventId, int page, int size) {
        return applicationClient.getApplications(eventId, page, size);

    }
}

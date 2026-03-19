package org.example.gateway_diplom.DTO.performanceDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceStatusUpdateRequest {

    @NotNull(message = "Статус не может быть пустым")
    private PerformanceStatus status;
}

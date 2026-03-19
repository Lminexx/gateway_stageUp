package org.example.gateway_diplom.Properties.webclient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "client.performance")
@Configuration
@Getter
@Setter
@Component
public class PerformanceClientProperties {
    private String baseUrl;
    private UserResources resources;
    @Getter @Setter
    public static class UserResources {
        private String performanceCreate;
        private String performanceUpdateStatus;
    }
}

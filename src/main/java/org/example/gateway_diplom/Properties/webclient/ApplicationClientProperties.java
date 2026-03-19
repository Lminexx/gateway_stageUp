package org.example.gateway_diplom.Properties.webclient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "client.application")
@Configuration
@Getter
@Setter
@Component
public class ApplicationClientProperties {
    private String baseUrl;
    private UserResources resources;
    @Getter @Setter
    public static class UserResources {
        private String applicationCreate;
        private String applicationUpdateStatus;
        private String applicationGet;
    }
}

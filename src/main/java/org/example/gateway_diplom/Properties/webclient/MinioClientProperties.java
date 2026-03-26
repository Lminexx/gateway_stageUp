package org.example.gateway_diplom.Properties.webclient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "client.minio")
@Configuration
@Getter
@Setter
@Component
public class MinioClientProperties {
    private String baseUrl;
    private MinioResources resources;
    @Getter @Setter
    public static class MinioResources {
        private String artist;
        private String organizer;
    }
}

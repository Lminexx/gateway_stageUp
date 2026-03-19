package org.example.gateway_diplom.Properties.webclient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "client.artist")
@Configuration
@Getter
@Setter
@Component
public class ArtistClientProperties {
    private String baseUrl;
    private AristResources resources;
    @Getter @Setter
    public static class AristResources {
        private String artistCreate;
        private String artistGet;
        private String artistUpdate;
        private String artistName;
    }
}

package org.example.gateway_diplom.Properties.webclient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "client.organization")
@Configuration
@Getter
@Setter
@Component
public class OrganizationClientProperties {
    private String baseUrl;
    private AristResources resources;
    @Getter @Setter
    public static class AristResources {
        private String organizationCreate;
        private String organizationGet;
        private String organizationUpdate;
        private String organizationUploadAvatar;
        private String organizationGetAvatar;
    }
}

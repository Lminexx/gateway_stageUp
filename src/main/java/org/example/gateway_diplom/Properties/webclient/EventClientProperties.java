package org.example.gateway_diplom.Properties.webclient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "client.event")
@Configuration
@Getter
@Setter
@Component
public class EventClientProperties {
    private String baseUrl;
    private UserResources resources;
    @Getter @Setter
    public static class UserResources {
        private String eventCreate;
        private String eventGets;
        private String eventGet;
        private String getMyEvents;
        private String updateEvent;
        private String deleteEvent;
    }
}
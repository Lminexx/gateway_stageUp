package org.example.gateway_diplom.Client;

import lombok.extern.slf4j.Slf4j;
import org.example.gateway_diplom.DTO.TokenResponse;
import org.example.gateway_diplom.DTO.usersDTO.UserDTO;
import org.example.gateway_diplom.DTO.usersDTO.UserRequest;
import org.example.gateway_diplom.Properties.webclient.UserClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Slf4j
@Component
public class UserClient {

    private final UserClientProperties clientProperties;
    private final RestTemplate restTemplate;

    public UserClient(UserClientProperties clientProperties, RestTemplate restTemplate) {
        this.clientProperties = clientProperties;
        this.restTemplate = restTemplate;
    }

    public UserDTO registerUser(UserRequest userReq) {
        log.info("Reg user {}", userReq.getUsername());
        return restTemplate.postForEntity(
                clientProperties.getBaseUrl() + clientProperties.getResources().getUserRegister(),
                userReq,
                UserDTO.class
        ).getBody();
    }

    public TokenResponse loginUser(UserRequest userReq) {
        return restTemplate.postForEntity(
                clientProperties.getBaseUrl() + clientProperties.getResources().getUserAuth(),
                userReq,
                TokenResponse.class
        ).getBody();
    }
}

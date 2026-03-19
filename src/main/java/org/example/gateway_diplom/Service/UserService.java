package org.example.gateway_diplom.Service;

import org.example.gateway_diplom.Client.UserClient;
import org.example.gateway_diplom.DTO.TokenResponse;
import org.example.gateway_diplom.DTO.usersDTO.UserDTO;
import org.example.gateway_diplom.DTO.usersDTO.UserRequest;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    private final UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public UserDTO createUser(UserRequest userReq) {
        return userClient.registerUser(userReq);
    }

    public TokenResponse loginUser(UserRequest userReq) {
        return userClient.loginUser(userReq);
    }
}

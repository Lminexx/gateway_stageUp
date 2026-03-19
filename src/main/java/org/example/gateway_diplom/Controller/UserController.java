package org.example.gateway_diplom.Controller;


import lombok.extern.slf4j.Slf4j;
import org.example.gateway_diplom.DTO.TokenResponse;
import org.example.gateway_diplom.DTO.usersDTO.UserDTO;
import org.example.gateway_diplom.DTO.usersDTO.UserRequest;
import org.example.gateway_diplom.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRequest userRequest) {
        log.info("Registering user: {}", userRequest);
        return ResponseEntity.ok(userService.createUser(userRequest));
    }
    @PostMapping("/login")
    public  ResponseEntity<TokenResponse> loginUser(@RequestBody UserRequest userRequest) {
        log.info("Login user: {}", userRequest);
        return ResponseEntity.ok(userService.loginUser(userRequest));
    }
}

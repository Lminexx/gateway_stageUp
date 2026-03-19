package org.example.gateway_diplom.DTO.usersDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String username;
    private String password;
    private String email;
    private String role;
}

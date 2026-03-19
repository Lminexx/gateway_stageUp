package org.example.gateway_diplom.DTO.profilesDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrganizationDTO {
    private UUID user_id;
    private String name;
    private String description;
    private String address;
    private String city;
    private String contact_phone;
    private String contact_email;
    private String website;
    private String logo_url;
}

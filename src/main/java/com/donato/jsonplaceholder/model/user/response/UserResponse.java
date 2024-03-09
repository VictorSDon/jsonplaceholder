package com.donato.jsonplaceholder.model.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private UserAddressResponse address;
    private String phone;
    private String website;
    private UserCompanyResponse company;
}

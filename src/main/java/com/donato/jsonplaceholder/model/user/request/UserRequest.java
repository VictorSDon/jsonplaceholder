package com.donato.jsonplaceholder.model.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String username;
    private String email;
    private UserAddressRequest address;
    private String phone;
    private String website;
    private UserCompanyRequest company;
}

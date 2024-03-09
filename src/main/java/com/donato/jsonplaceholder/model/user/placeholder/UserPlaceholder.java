package com.donato.jsonplaceholder.model.user.placeholder;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPlaceholder {
    private Long id;
    private String name;
    private String username;
    private String email;
    private UserAddressPlaceholder address;
    private String phone;
    private String website;
    private UserCompanyPlaceholder company;
}

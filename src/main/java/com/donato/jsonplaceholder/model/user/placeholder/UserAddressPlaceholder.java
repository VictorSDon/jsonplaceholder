package com.donato.jsonplaceholder.model.user.placeholder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressPlaceholder {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private UserAddressGeoPlaceholder geo;
}

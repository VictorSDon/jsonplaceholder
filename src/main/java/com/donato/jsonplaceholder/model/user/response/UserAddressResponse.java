package com.donato.jsonplaceholder.model.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserAddressResponse {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private UserAddressGeoResponse geo;
}

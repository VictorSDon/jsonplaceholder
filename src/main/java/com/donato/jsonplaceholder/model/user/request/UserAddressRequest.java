package com.donato.jsonplaceholder.model.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressRequest {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private UserAddressGeoRequest geo;
}

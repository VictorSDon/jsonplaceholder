package com.donato.jsonplaceholder.model.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserAddressGeoResponse {
    private String lat;
    private String lng;
}

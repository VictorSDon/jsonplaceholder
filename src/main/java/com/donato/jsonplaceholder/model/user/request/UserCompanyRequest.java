package com.donato.jsonplaceholder.model.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCompanyRequest {
    private String name;
    private String catchphrase;
    private String bs;
}

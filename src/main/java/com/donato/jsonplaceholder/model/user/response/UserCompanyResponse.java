package com.donato.jsonplaceholder.model.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCompanyResponse {
    private String name;
    private String catchPhrase;
    private String bs;
}

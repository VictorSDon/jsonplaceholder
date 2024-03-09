package com.donato.jsonplaceholder.model.user.placeholder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCompanyPlaceholder {
    private String name;
    private String catchphrase;
    private String bs;
}

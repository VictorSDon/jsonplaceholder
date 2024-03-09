package com.donato.jsonplaceholder.model.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponse {
    private Long id;
    private String title;
    private String body;
}

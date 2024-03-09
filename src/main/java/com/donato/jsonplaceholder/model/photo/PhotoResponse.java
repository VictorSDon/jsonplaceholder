package com.donato.jsonplaceholder.model.photo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponse {
    private Long id;
    private String title;
    private String url;
    private String thumbnailUrl;
}

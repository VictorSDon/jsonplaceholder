package com.donato.jsonplaceholder.model.photo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhotoPlaceholder {
    private Long albumId;
    private Long id;
    private String title;
    private String url;
    private String thumbnailUrl;
}

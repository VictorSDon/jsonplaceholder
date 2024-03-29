package com.donato.jsonplaceholder.model.photo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoRequest {
    private String title;
    private String url;
    private String thumbnailUrl;
    private Long albumId;
}

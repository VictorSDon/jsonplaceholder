package com.donato.jsonplaceholder.model.album;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumResponse {
    private Long id;
    private String title;
}

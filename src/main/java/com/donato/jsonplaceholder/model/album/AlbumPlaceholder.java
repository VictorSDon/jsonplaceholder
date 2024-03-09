package com.donato.jsonplaceholder.model.album;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AlbumPlaceholder {
    private Long userId;
    private Long id;
    private String title;
}

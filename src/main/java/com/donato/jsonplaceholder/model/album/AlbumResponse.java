package com.donato.jsonplaceholder.model.album;

import com.donato.jsonplaceholder.model.photo.PhotoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumResponse {
    private Long id;
    private String title;
    private List<PhotoResponse> photos;
}

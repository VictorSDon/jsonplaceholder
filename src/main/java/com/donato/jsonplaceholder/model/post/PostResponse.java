package com.donato.jsonplaceholder.model.post;

import com.donato.jsonplaceholder.model.comment.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponse {
    private Long id;
    private String title;
    private String body;
    private List<CommentResponse> comments;
}

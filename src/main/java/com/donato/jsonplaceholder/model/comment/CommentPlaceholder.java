package com.donato.jsonplaceholder.model.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentPlaceholder {
    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;
}

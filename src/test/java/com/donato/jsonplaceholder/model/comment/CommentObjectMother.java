package com.donato.jsonplaceholder.model.comment;

import com.donato.jsonplaceholder.model.post.PostObjectMother;

public class CommentObjectMother {
    public static CommentDomain domain(){
        return CommentDomain.builder()
                .id(123L)
                .body("test_body")
                .name("test_name")
                .email("test_email")
                .build();
    }
    public static CommentResponse response(){
        return CommentResponse.builder()
                .id(123L)
                .body("test_body")
                .email("test_email")
                .name("test_name")
                .build();
    }
    public static CommentRequest request(){
        return CommentRequest.builder()
                .body("test_body")
                .name("test_name")
                .email("test_email")
                .postId(1L)
                .build();
    }
    public static CommentDomain domainToSave(){
        return CommentDomain.builder()
                .email("test_email")
                .name("test_name")
                .body("test_body")
                .post(PostObjectMother.domain())
                .build();
    }
    public static CommentPlaceholder commentPlaceholder(){
        return CommentPlaceholder.builder()
                .postId(9L)
                .id(1L)
                .email("test_email")
                .name("test_name")
                .body("test_body").build();
    }
    public static CommentDomain parseComment(){
        return CommentDomain.builder()
                .name("test_name")
                .email("test_email")
                .body("test_body").build();
    }
}

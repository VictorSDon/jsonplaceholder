package com.donato.jsonplaceholder.model.post;

import com.donato.jsonplaceholder.model.comment.CommentObjectMother;
import com.donato.jsonplaceholder.model.user.UserObjectMother;

import java.util.ArrayList;
import java.util.List;

public class PostObjectMother {
    public static PostDomain domain(){
        return PostDomain.builder()
                .id(123L)
                .body("test_body")
                .title("test_title")
                .build();
    }
    public static PostResponse response(){
        return PostResponse.builder()
                .id(123L)
                .body("test_body")
                .title("test_title")
                .comments(new ArrayList<>())
                .build();
    }
    public static PostDomain domainToSave(){
        return PostDomain.builder()
                .body("test_body")
                .user(UserObjectMother.domain())
                .title("test_title")
                .build();
    }
    public static PostRequest request(){
        return PostRequest.builder()
                .userId(12L)
                .body("test_body")
                .title("test_title")
                .build();
    }
    public static PostPlaceholder postPlaceholder(){
        return PostPlaceholder.builder()
                .userId(12L)
                .id(1L)
                .body("test_body")
                .title("test_title").build();
    }
    public static PostDomain parsePost(){
        return PostDomain.builder()
                .title("test_title")
                .body("test_body")
                .comments(List.of(CommentObjectMother.parseComment())).build();
    }
}

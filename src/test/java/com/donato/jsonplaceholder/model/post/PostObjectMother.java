package com.donato.jsonplaceholder.model.post;

import com.donato.jsonplaceholder.model.user.UserObjectMother;

import java.util.ArrayList;

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
}

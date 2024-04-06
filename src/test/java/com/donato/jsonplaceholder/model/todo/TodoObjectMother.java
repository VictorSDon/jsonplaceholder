package com.donato.jsonplaceholder.model.todo;

import com.donato.jsonplaceholder.model.user.UserObjectMother;

public class TodoObjectMother {
    public static TodoDomain domain(){
        return TodoDomain.builder()
                .id(123L)
                .title("test_title")
                .completed(false)
                .build();
    }
    public static TodoResponse response(){
        return TodoResponse.builder()
                .id(123L)
                .title("test_title")
                .completed(false)
                .build();
    }
    public static TodoRequest request(){
        return TodoRequest.builder()
                .title("test_title")
                .completed(false)
                .userId(12L)
                .build();
    }
    public static TodoDomain domainToSave(){
        return TodoDomain.builder()
                .title("test_title")
                .completed(false)
                .user(UserObjectMother.domain())
                .build();
    }
}

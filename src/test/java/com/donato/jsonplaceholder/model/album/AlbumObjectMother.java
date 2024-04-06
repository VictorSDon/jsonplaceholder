package com.donato.jsonplaceholder.model.album;

import com.donato.jsonplaceholder.model.user.UserObjectMother;

import java.util.ArrayList;

public class AlbumObjectMother {
    public static AlbumDomain albumDomain(){
        return AlbumDomain.builder()
                .id(12L)
                .title("test_title")
                .photos(new ArrayList<>())
                .build();
    }
    public static AlbumDomain domain(){
        return AlbumDomain.builder()
                .id(123L)
                .title("test_title")
                .build();
    }
    public static AlbumResponse response(){
        return AlbumResponse.builder()
                .id(123l)
                .title("test_title")
                .build();
    }
    public static AlbumRequest request(){
        return AlbumRequest.builder()
                .title("test_title")
                .userId(12L)
                .build();
    }
    public static AlbumDomain domainToSave(){
        return AlbumDomain.builder()
                .title("test_title")
                .user(UserObjectMother.domain())
                .build();
    }
}

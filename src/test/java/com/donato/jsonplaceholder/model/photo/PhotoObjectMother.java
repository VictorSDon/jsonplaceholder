package com.donato.jsonplaceholder.model.photo;

import com.donato.jsonplaceholder.model.album.AlbumObjectMother;

public class PhotoObjectMother {
    public static PhotoDomain domain(){
        return PhotoDomain.builder()
                .id(123L)
                .url("test_url")
                .title("test_title")
                .thumbnailUrl("test_thumbnailUrl")
                .build();
    }
    public static PhotoResponse response(){
        return PhotoResponse.builder()
                .id(123L)
                .url("test_url")
                .thumbnailUrl("test_thumbnailUrl")
                .title("test_title")
                .build();
    }
    public static PhotoRequest request(){
        return PhotoRequest.builder()
                .url("test_url")
                .thumbnailUrl("test_thumbnailUrl")
                .title("test_title")
                .albumId(12L)
                .build();
    }
    public static PhotoDomain domainToSave(){
        return PhotoDomain.builder()
                .album(AlbumObjectMother.albumDomain())
                .title("test_title")
                .url("test_url")
                .thumbnailUrl("test_thumbnailUrl")
                .build();
    }
    public static PhotoPlaceholder photoPlaceholder(){
        return PhotoPlaceholder.builder().albumId(13L).id(1L)
                .title("test_title")
                .url("test_url")
                .thumbnailUrl("test_thumbnailUrl").build();
    }
    public static PhotoDomain parsePhoto(){
        return PhotoDomain.builder()
                .title("test_title")
                .url("test_url")
                .thumbnailUrl("test_thumbnail").build();
    }
}

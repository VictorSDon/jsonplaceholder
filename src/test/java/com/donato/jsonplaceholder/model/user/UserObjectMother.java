package com.donato.jsonplaceholder.model.user;

import com.donato.jsonplaceholder.model.album.AlbumObjectMother;
import com.donato.jsonplaceholder.model.post.PostObjectMother;
import com.donato.jsonplaceholder.model.todo.TodoObjectMother;
import com.donato.jsonplaceholder.model.user.domain.UserAddressDomain;
import com.donato.jsonplaceholder.model.user.domain.UserAddressGeoDomain;
import com.donato.jsonplaceholder.model.user.domain.UserCompanyDomain;
import com.donato.jsonplaceholder.model.user.domain.UserDomain;
import com.donato.jsonplaceholder.model.user.placeholder.UserAddressGeoPlaceholder;
import com.donato.jsonplaceholder.model.user.placeholder.UserAddressPlaceholder;
import com.donato.jsonplaceholder.model.user.placeholder.UserCompanyPlaceholder;
import com.donato.jsonplaceholder.model.user.placeholder.UserPlaceholder;
import com.donato.jsonplaceholder.model.user.request.UserAddressGeoRequest;
import com.donato.jsonplaceholder.model.user.request.UserAddressRequest;
import com.donato.jsonplaceholder.model.user.request.UserCompanyRequest;
import com.donato.jsonplaceholder.model.user.request.UserRequest;
import com.donato.jsonplaceholder.model.user.response.UserAddressGeoResponse;
import com.donato.jsonplaceholder.model.user.response.UserAddressResponse;
import com.donato.jsonplaceholder.model.user.response.UserCompanyResponse;
import com.donato.jsonplaceholder.model.user.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class UserObjectMother {
    public static UserDomain domain(){
        return UserDomain.builder()
                .id(12L)
                .name("test_name")
                .username("test_username")
                .website("test_website")
                .email("test_email")
                .address(UserAddressDomain.builder()
                        .street("test_street")
                        .suite("test_suite")
                        .city("test_city")
                        .zipcode("test_zipcode")
                        .geo(UserAddressGeoDomain.builder()
                                .lng("test_lng")
                                .lat("test_lat")
                                .build())
                        .build())
                .phone("test_phone")
                .company(UserCompanyDomain.builder()
                        .name("test_name")
                        .catchphrase("test_catchphrase")
                        .bs("test_bs")
                        .build())
                .build();
    }
    public static UserResponse response(){
        return UserResponse.builder()
                .id(12L)
                .name("test_name")
                .username("test_username")
                .website("test_website")
                .email("test_email")
                .address(UserAddressResponse.builder()
                        .street("test_street")
                        .suite("test_suite")
                        .city("test_city")
                        .zipcode("test_zipcode")
                        .geo(UserAddressGeoResponse.builder()
                                .lng("test_lng")
                                .lat("test_lat")
                                .build())
                        .build())
                .phone("test_phone")
                .company(UserCompanyResponse.builder()
                        .name("test_name")
                        .catchphrase("test_catchphrase")
                        .bs("test_bs")
                        .build())
                .todos(new ArrayList<>())
                .posts(new ArrayList<>())
                .albums(new ArrayList<>())
                .build();
    }
    public static UserDomain domainToSave(){
        return UserDomain.builder()
                .name("test_name")
                .username("test_username")
                .website("test_website")
                .email("test_email")
                .address(UserAddressDomain.builder()
                        .street("test_street")
                        .suite("test_suite")
                        .city("test_city")
                        .zipcode("test_zipcode")
                        .geo(UserAddressGeoDomain.builder()
                                .lng("test_lng")
                                .lat("test_lat")
                                .build())
                        .build())
                .phone("test_phone")
                .company(UserCompanyDomain.builder()
                        .name("test_name")
                        .catchphrase("test_catchphrase")
                        .bs("test_bs")
                        .build())
                .build();
    }
    public static UserRequest request(){
        return UserRequest.builder()
                .name("test_name")
                .username("test_username")
                .website("test_website")
                .email("test_email")
                .address(UserAddressRequest.builder()
                        .street("test_street")
                        .suite("test_suite")
                        .city("test_city")
                        .zipcode("test_zipcode")
                        .geo(UserAddressGeoRequest.builder()
                                .lng("test_lng")
                                .lat("test_lat")
                                .build())
                        .build())
                .phone("test_phone")
                .company(UserCompanyRequest.builder()
                        .name("test_name")
                        .catchphrase("test_catchphrase")
                        .bs("test_bs")
                        .build())
                .build();
    }
    public static UserPlaceholder userPlaceholder(){
        return UserPlaceholder.builder()
                .id(12L)
                .name("test_name")
                .username("test_username")
                .email("test_email").address(UserAddressPlaceholder.builder()
                        .street("test_street")
                        .city("test_city")
                        .suite("test_suite").zipcode("test_zipcode")
                        .geo(UserAddressGeoPlaceholder.builder()
                                .lat("test_lat")
                                .lng("test_lng").build())
                        .build())
                .phone("test_phone")
                .website("test_website")
                .company(UserCompanyPlaceholder.builder()
                        .name("test_name")
                        .catchphrase("test_catchphrase")
                        .bs("test_bs")
                        .build())
                .build();
    }
    public static UserDomain CurrentUser(){
       return UserDomain.builder().name("test_name")
                .username("test_username")
                .website("test_website")
                .email("test_email")
                .address(UserAddressDomain.builder()
                        .street("test_street")
                        .suite("test_suite")
                        .city("test_city")
                        .zipcode("test_zipcode")
                        .geo(UserAddressGeoDomain.builder()
                                .lng("test_lng")
                                .lat("test_lat")
                                .build())
                        .build())
                .phone("test_phone")
                .company(UserCompanyDomain.builder()
                        .name("test_name")
                        .catchphrase("test_catchphrase")
                        .bs("test_bs")
                        .build())
               .albums(List.of(AlbumObjectMother.parseAlbum()))
               .todos(List.of(TodoObjectMother.parseTodo()))
               .posts(List.of(PostObjectMother.parsePost()))
                .build();
    }
}

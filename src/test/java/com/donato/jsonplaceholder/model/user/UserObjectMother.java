package com.donato.jsonplaceholder.model.user;

import com.donato.jsonplaceholder.model.user.domain.UserAddressDomain;
import com.donato.jsonplaceholder.model.user.domain.UserAddressGeoDomain;
import com.donato.jsonplaceholder.model.user.domain.UserCompanyDomain;
import com.donato.jsonplaceholder.model.user.domain.UserDomain;

public class UserObjectMother {
    public static UserDomain domain(){
        return UserDomain.builder()
                .id(12L)
                .name("test_name")
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
}

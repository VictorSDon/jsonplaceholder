package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.user.domain.UserAddressDomain;
import com.donato.jsonplaceholder.model.user.domain.UserAddressGeoDomain;
import com.donato.jsonplaceholder.model.user.domain.UserCompanyDomain;
import com.donato.jsonplaceholder.model.user.domain.UserDomain;
import com.donato.jsonplaceholder.model.user.request.UserRequest;
import com.donato.jsonplaceholder.model.user.response.UserAddressGeoResponse;
import com.donato.jsonplaceholder.model.user.response.UserAddressResponse;
import com.donato.jsonplaceholder.model.user.response.UserCompanyResponse;
import com.donato.jsonplaceholder.model.user.response.UserResponse;
import com.donato.jsonplaceholder.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<UserResponse> listAll(){
        List<UserDomain> domainList = repository.findAll();
        List<UserResponse> response = domainList.stream()
                .map(this::parseDomainToResponse)
                .toList();
        return response;
    }
    public UserResponse getById(Long id){
        UserDomain domain = repository.findById(id).orElseThrow();
        return parseDomainToResponse(domain);
    }
    public UserResponse create(UserRequest request){
        UserDomain domain = UserDomain.builder().name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .address(UserAddressDomain.builder()
                        .street(request.getAddress().getStreet())
                        .suite(request.getAddress().getSuite())
                        .city(request.getAddress().getCity())
                        .zipcode(request.getAddress().getZipcode())
                        .geo(UserAddressGeoDomain.builder()
                                .lng(request.getAddress().getGeo().getLng())
                                .lat(request.getAddress().getGeo().getLat()).build())
                        .build())
                .phone(request.getPhone())
                .website(request.getWebsite())
                .company(UserCompanyDomain.builder()
                        .name(request.getCompany().getName())
                        .catchphrase(request.getCompany().getCatchphrase())
                        .bs(request.getCompany().getBs()).build())
                .build();
        domain = repository.save(domain);
        return parseDomainToResponse(domain);
    }
    public void deleteById(Long id){repository.deleteById(id);}

    public UserResponse update(UserRequest request, Long id){
        UserDomain domain = UserDomain.builder().id(id).name(request.getName())
        .username(request.getUsername())
                .email(request.getEmail())
                .address(UserAddressDomain.builder()
                        .street(request.getAddress().getStreet())
                        .suite(request.getAddress().getSuite())
                        .city(request.getAddress().getCity())
                        .zipcode(request.getAddress().getZipcode())
                        .geo(UserAddressGeoDomain.builder()
                                .lng(request.getAddress().getGeo().getLng())
                                .lat(request.getAddress().getGeo().getLat()).build())
                        .build())
                .phone(request.getPhone())
                .website(request.getWebsite())
                .company(UserCompanyDomain.builder()
                        .name(request.getCompany().getName())
                        .catchphrase(request.getCompany().getCatchphrase())
                        .bs(request.getCompany().getBs()).build())
                .build();
        domain = repository.save(domain);
        return parseDomainToResponse(domain);
    }

    public UserResponse parseDomainToResponse(UserDomain domain){
        return UserResponse.builder()
                .id(domain.getId())
                .name(domain.getName())
                .username(domain.getUsername())
                .email(domain.getEmail())
                .address(UserAddressResponse.builder()
                        .street(domain.getAddress().getStreet())
                        .suite(domain.getAddress().getSuite())
                        .city(domain.getAddress().getCity())
                        .zipcode(domain.getAddress().getZipcode())
                        .geo(UserAddressGeoResponse.builder()
                                .lng(domain.getAddress().getGeo().getLng())
                                .lat(domain.getAddress().getGeo().getLat()).build())
                        .build())
                .phone(domain.getPhone())
                .website(domain.getWebsite())
                .company(UserCompanyResponse.builder()
                        .name(domain.getCompany().getName())
                        .catchphrase(domain.getCompany().getCatchphrase())
                        .bs(domain.getCompany().getBs()).build())
                .build();
    }
}

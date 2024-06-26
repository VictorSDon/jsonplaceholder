package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.album.AlbumDomain;
import com.donato.jsonplaceholder.model.album.AlbumRequest;
import com.donato.jsonplaceholder.model.album.AlbumResponse;
import com.donato.jsonplaceholder.model.user.domain.UserDomain;
import com.donato.jsonplaceholder.repository.jpa.AlbumRepository;
import com.donato.jsonplaceholder.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//regras de negocio
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository repository;
    private final UserRepository userRepository;

    public List<AlbumResponse> listAll(){
        List<AlbumDomain> domainlist = repository.findAll();
        List<AlbumResponse> response = domainlist.stream()
                .map(this::parseDomainToResponse)
                .toList();
        return response;
    }

    public AlbumResponse getById(Long id){
        AlbumDomain domain = repository.getById(id);
        return parseDomainToResponse(domain);

    }

    public AlbumResponse create(AlbumRequest request){
        AlbumDomain domain = AlbumDomain.builder()
                .title(request.getTitle()).build();
        UserDomain userDomain = userRepository.findById(request.getUserId()).orElseThrow();
        domain.setUser(userDomain);
        domain = repository.save(domain);
        return parseDomainToResponse(domain);
    }

    public void deleteById(Long id){repository.deleteById(id);}

    public AlbumResponse update(AlbumRequest request, Long id){
        AlbumDomain domain = AlbumDomain.builder()
                .id(id)
                .title(request.getTitle()).build();
        domain = repository.save(domain);
        return parseDomainToResponse(domain);
    }

    public AlbumResponse parseDomainToResponse (AlbumDomain domain){
        return AlbumResponse.builder()
                .id(domain.getId())
                .title(domain.getTitle()).build();

    }


}

package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.photo.PhotoDomain;
import com.donato.jsonplaceholder.model.photo.PhotoRequest;
import com.donato.jsonplaceholder.model.photo.PhotoResponse;
import com.donato.jsonplaceholder.repository.jpa.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository repository;

    public List<PhotoResponse> listAll(){
        List<PhotoDomain> domainList = repository.findAll();
        List<PhotoResponse> response = domainList.stream()
                .map(this::parseDomainToResponse)
                .toList();
        return response;
    }
    public PhotoResponse getById(Long id){
        PhotoDomain domain = repository.getById(id);
        return parseDomainToResponse(domain);
    }

    public PhotoResponse create(PhotoRequest request){
        PhotoDomain domain = PhotoDomain.builder()
                .title(request.getTitle())
                .url(request.getUrl())
                .thumbnailUrl(request.getThumbnailUrl()).build();
        domain = repository.save(domain);
        return parseDomainToResponse(domain);
    }

    public void deleteById(Long id){repository.deleteById(id);}

    public PhotoResponse update(PhotoRequest request, Long id){
        PhotoDomain domain = PhotoDomain.builder().id(id)
                .title(request.getTitle())
                .url(request.getUrl())
                .thumbnailUrl(request.getThumbnailUrl()).build();
        domain = repository.save(domain);
        return parseDomainToResponse(domain);
    }

    public PhotoResponse parseDomainToResponse(PhotoDomain domain){
        return PhotoResponse.builder().id(domain.getId())
                .title(domain.getTitle())
                .url(domain.getUrl())
                .thumbnailUrl(domain.getThumbnailUrl()).build();
    }
}

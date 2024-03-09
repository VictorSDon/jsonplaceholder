package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.comment.CommentDomain;
import com.donato.jsonplaceholder.model.comment.CommentRequest;
import com.donato.jsonplaceholder.model.comment.CommentResponse;
import com.donato.jsonplaceholder.repository.jpa.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;

    public List<CommentResponse> listAll(){
        List<CommentDomain> domainList = repository.findAll();
        List<CommentResponse> response = domainList.stream()
                .map(this::parseDomainToResponse)
                .toList();
        return response;
    }

    public CommentResponse getById(Long id){
        CommentDomain domain = repository.getById(id);
        return parseDomainToResponse(domain);
    }

    public CommentResponse create(CommentRequest request){
        CommentDomain domain = CommentDomain.builder()
                .name(request.getName())
                .email(request.getEmail())
                .body(request.getBody()).build();
        domain = repository.save(domain);
        return parseDomainToResponse(domain);
    }

    public void deleteById(Long id){repository.deleteById(id);}

    public CommentResponse uptade(CommentRequest request, Long id){
        CommentDomain domain = CommentDomain.builder()
                .id(id)
                .name(request.getName())
                .email(request.getEmail())
                .body(request.getBody()).build();
        domain = repository.save(domain);
        return parseDomainToResponse(domain);
    }

    public CommentResponse parseDomainToResponse(CommentDomain domain){
        return CommentResponse.builder()
                .id(domain.getId())
                .name(domain.getName())
                .email(domain.getEmail())
                .body(domain.getBody()).build();
    }
}

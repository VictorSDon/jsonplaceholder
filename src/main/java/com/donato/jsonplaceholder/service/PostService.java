package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.comment.CommentDomain;
import com.donato.jsonplaceholder.model.comment.CommentResponse;
import com.donato.jsonplaceholder.model.user.domain.UserDomain;
import com.donato.jsonplaceholder.repository.jpa.PostRepository;
import com.donato.jsonplaceholder.model.post.PostDomain;
import com.donato.jsonplaceholder.model.post.PostRequest;
import com.donato.jsonplaceholder.model.post.PostResponse;
import com.donato.jsonplaceholder.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final UserRepository userRepository;

    public List<PostResponse> listAll(){
        List<PostDomain> domainList = repository.findAll();
        List<PostResponse> response = domainList.stream()
                .map(this::parseDomainToResponse)
                .toList();
        return response;
    }

    public PostResponse getById(Long id){
        PostDomain domain = repository.getById(id);
        return parseDomainToResponse(domain);
    }

    public PostResponse create(PostRequest request){
        PostDomain domain = PostDomain.builder()
                .title(request.getTitle())
                .body(request.getBody()).build();
        UserDomain userDomain = userRepository.findById(request.getUserId()).orElseThrow();
        domain.setUser(userDomain);
        domain = repository.save(domain);
        return parseDomainToResponse(domain);
    }

    public void deleteById(Long id){repository.deleteById(id);}

    public PostResponse update(PostRequest request, Long id){
        PostDomain domain = PostDomain.builder()
                .id(id)
                .title(request.getTitle())
                .body(request.getBody())
                .build();
        domain = repository.save(domain);
        return parseDomainToResponse(domain);
    }

    public PostResponse parseDomainToResponse(PostDomain domain){
        List<CommentResponse> comments = new ArrayList<>();
        if (Objects.nonNull(domain.getComments())){
            comments = domain.getComments().stream().map(commentDomain -> CommentResponse.builder()
                    .id(commentDomain.getId())
                    .name(commentDomain.getName())
                    .email(commentDomain.getEmail())
                    .body(commentDomain.getBody())
                    .build()).toList();
        }
        return PostResponse.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .body(domain.getBody())
                .comments(comments)
                .build();

    }
}

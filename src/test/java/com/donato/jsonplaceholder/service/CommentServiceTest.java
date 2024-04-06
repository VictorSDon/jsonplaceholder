package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.comment.CommentDomain;
import com.donato.jsonplaceholder.model.comment.CommentObjectMother;
import com.donato.jsonplaceholder.model.comment.CommentRequest;
import com.donato.jsonplaceholder.model.comment.CommentResponse;
import com.donato.jsonplaceholder.model.post.PostDomain;
import com.donato.jsonplaceholder.model.post.PostObjectMother;
import com.donato.jsonplaceholder.repository.jpa.CommentRepository;
import com.donato.jsonplaceholder.repository.jpa.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private CommentService target;

    private CommentDomain domain;
    private CommentResponse response;
    private Long id;
    private Long postId;
    private PostDomain postDomain;
    private CommentRequest request;
    private CommentDomain domainToSave;

    @BeforeEach
    public void setup(){

        domain = CommentObjectMother.domain();
        response = CommentObjectMother.response();
        id = 123l;
        postId = 1L;
        postDomain = PostObjectMother.domain();
        request = CommentObjectMother.request();
        domainToSave = CommentObjectMother.domainToSave();
    }
    @Test
    public void shouldListAll(){
        when(commentRepository.findAll()).thenReturn(List.of(domain));

        List<CommentResponse> res = target.listAll();

        assert (res.equals(List.of(response)));
        verify(commentRepository).findAll();
    }
    @Test
    public void shouldGetById(){
        when(commentRepository.getById(id)).thenReturn(domain);

        CommentResponse res = target.getById(id);

        assert (res.equals(response));
        verify(commentRepository).getById(id);
    }
    @Test
    public void shouldCreate(){
        when(commentRepository.save(domainToSave)).thenReturn(domain);

        when(postRepository.findById(postId)).thenReturn(Optional.of(postDomain));

        CommentResponse res = target.create(request);

        assert (res.equals(response));
        verify(commentRepository).save(domainToSave);
        verify(postRepository).findById(postId);
    }
    @Test
    public void shouldDelete(){
        target.deleteById(id);
        verify(commentRepository).deleteById(id);
    }
    @Test
    public void shouldUpdate(){
        domainToSave.setPost(null);
        when(commentRepository.save(domain)).thenReturn(domain);

        CommentResponse res = target.update(request, id);

        assert (res.equals(response));
        verify(commentRepository).save(domain);
    }
}

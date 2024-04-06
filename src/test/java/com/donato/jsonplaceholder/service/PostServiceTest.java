package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.photo.PhotoDomain;
import com.donato.jsonplaceholder.model.post.PostDomain;
import com.donato.jsonplaceholder.model.post.PostObjectMother;
import com.donato.jsonplaceholder.model.post.PostRequest;
import com.donato.jsonplaceholder.model.post.PostResponse;
import com.donato.jsonplaceholder.model.user.UserObjectMother;
import com.donato.jsonplaceholder.model.user.domain.UserAddressDomain;
import com.donato.jsonplaceholder.model.user.domain.UserAddressGeoDomain;
import com.donato.jsonplaceholder.model.user.domain.UserCompanyDomain;
import com.donato.jsonplaceholder.model.user.domain.UserDomain;
import com.donato.jsonplaceholder.repository.jpa.PostRepository;
import com.donato.jsonplaceholder.repository.jpa.UserRepository;
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
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private PostService target;

    private PostDomain domain;
    private PostResponse response;
    private Long id;
    private Long userId;
    private UserDomain userDomain;
    private PostDomain domainToSave;
    private PostRequest request;

    @BeforeEach
    public void setup(){
        domain = PostObjectMother.domain();
        response = PostObjectMother.response();
        id = 123L;
        userId = 12L;
        userDomain = UserObjectMother.domain();
        domainToSave = PostObjectMother.domainToSave();
        request = PostObjectMother.request();
    }

    @Test
    public void shouldListAll(){
        when(postRepository.findAll()).thenReturn(List.of(domain));

        List<PostResponse> res = target.listAll();

        assert (res.equals(List.of(response)));
        verify(postRepository).findAll();
    }
    @Test
    public void shouldGetById(){
        when(postRepository.getById(id)).thenReturn(domain);

        PostResponse res = target.getById(id);

        assert (res.equals(response));
        verify(postRepository).getById(id);
    }
    @Test
    public void shouldCreate(){
        when(postRepository.save(domainToSave)).thenReturn(domain);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userDomain));

        PostResponse res = target.create(request);

        assert (res.equals(response));
        verify(postRepository).save(domainToSave);
        verify(userRepository).findById(userId);
    }
    @Test
    public void shouldDelete(){
        target.deleteById(id);
        verify(postRepository).deleteById(id);
    }
    @Test
    public void shouldUpdate(){
        when(postRepository.save(domain)).thenReturn(domain);

        PostResponse res = target.update(request, id);

        assert (res.equals(response));
        verify(postRepository).save(domain);
    }
}

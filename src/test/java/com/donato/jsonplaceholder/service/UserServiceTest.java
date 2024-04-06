package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.user.UserObjectMother;
import com.donato.jsonplaceholder.model.user.domain.UserDomain;
import com.donato.jsonplaceholder.model.user.request.UserRequest;
import com.donato.jsonplaceholder.model.user.response.UserResponse;
import com.donato.jsonplaceholder.repository.jpa.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService target;

    private UserDomain domain;
    private UserResponse response;
    private Long id;
    private UserDomain domainToSave;
    private UserRequest request;

    @BeforeEach
    public void setup(){
        domain = UserObjectMother.domain();
        response = UserObjectMother.response();
        id = 12L;
        domainToSave = UserObjectMother.domainToSave();
        request = UserObjectMother.request();
    }
    @Test
    public void shouldListAll(){
        when(userRepository.findAll()).thenReturn(List.of(domain));

        List<UserResponse> res = target.listAll();

        assertEquals(res,List.of(response));
        verify(userRepository).findAll();
    }
    @Test
    public void shouldGetById(){
        when(userRepository.findById(id)).thenReturn(Optional.of(domain));

        UserResponse res = target.getById(id);

        assertEquals(res, response);
        verify(userRepository).findById(id);
    }
    @Test
    public void shouldCreate(){
        when(userRepository.save(domainToSave)).thenReturn(domain);

        UserResponse res = target.create(request);

        assertEquals(res, response);
        verify(userRepository).save(domainToSave);
    }
    @Test
    public void shouldDeleteById(){
        target.deleteById(id);
        verify(userRepository).deleteById(id);
    }
    @Test
    public void shouldUpdate(){
        when(userRepository.save(domain)).thenReturn(domain);

        UserResponse res = target.update(request, id);

        assertEquals(res, response);
        verify(userRepository).save(domain);
    }
}

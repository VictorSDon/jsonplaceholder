package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.todo.TodoDomain;
import com.donato.jsonplaceholder.model.todo.TodoObjectMother;
import com.donato.jsonplaceholder.model.todo.TodoRequest;
import com.donato.jsonplaceholder.model.todo.TodoResponse;
import com.donato.jsonplaceholder.model.user.UserObjectMother;
import com.donato.jsonplaceholder.model.user.domain.UserAddressDomain;
import com.donato.jsonplaceholder.model.user.domain.UserAddressGeoDomain;
import com.donato.jsonplaceholder.model.user.domain.UserCompanyDomain;
import com.donato.jsonplaceholder.model.user.domain.UserDomain;
import com.donato.jsonplaceholder.repository.jpa.TodoRepository;
import com.donato.jsonplaceholder.repository.jpa.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private TodoService target;

    private TodoDomain domain;
    private TodoResponse response;
    private Long id;
    private UserDomain userDomain;
    private TodoRequest request;
    private Long userId;
    private TodoDomain domainToSave;

    @BeforeEach
    public void setUp(){
        domain = TodoObjectMother.domain();
        response = TodoObjectMother.response();
        id = 123L;
        userId = 12L;
        userDomain = UserObjectMother.domain();
        request = TodoObjectMother.request();
        domainToSave = TodoObjectMother.domainToSave();
    }
    @Test
    public void shouldListAll(){
        //Setup
        when(todoRepository.findAll()).thenReturn(List.of(domain));

        //Running the target function
        List<TodoResponse> res = target.listAll();

        //Check the desired behaviour
        assert(res.equals(List.of(response)));
        verify(todoRepository).findAll();
    }
    @Test
    public void shouldGetById(){
        when(todoRepository.findById(id)).thenReturn(Optional.of(domain));

        TodoResponse res = target.getById(id);

        assert(res.equals(response));
        verify(todoRepository).findById(id);
    }
    @Test
    public void shouldCreate(){
        when(todoRepository.save(domainToSave)).thenReturn(domain);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userDomain));

        TodoResponse res = target.create(request);

        assert (res.equals(response));
        verify(todoRepository).save(domainToSave);
        verify(userRepository).findById(userId);
    }
    @Test
    public void shouldDelete(){
        target.deleteById(id);
        verify(todoRepository).deleteById(id);
    }
    @Test
    public void shouldUpdate(){
        domainToSave.setUser(null);
        when(todoRepository.save(domain)).thenReturn(domain);

        TodoResponse res = target.update(request, id);

        assert (res.equals(response));
        verify(todoRepository).save(domain);
    }
}

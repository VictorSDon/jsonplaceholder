package com.donato.jsonplaceholder.service;

import com.donato.jsonplaceholder.model.album.AlbumDomain;
import com.donato.jsonplaceholder.model.album.AlbumObjectMother;
import com.donato.jsonplaceholder.model.album.AlbumRequest;
import com.donato.jsonplaceholder.model.album.AlbumResponse;
import com.donato.jsonplaceholder.model.user.UserObjectMother;
import com.donato.jsonplaceholder.model.user.domain.UserAddressDomain;
import com.donato.jsonplaceholder.model.user.domain.UserAddressGeoDomain;
import com.donato.jsonplaceholder.model.user.domain.UserCompanyDomain;
import com.donato.jsonplaceholder.model.user.domain.UserDomain;
import com.donato.jsonplaceholder.repository.jpa.AlbumRepository;
import com.donato.jsonplaceholder.repository.jpa.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.internal.matchers.Null;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AlbumServiceTest {
    @Mock
    private AlbumRepository albumRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AlbumService target;

    private AlbumDomain domain;
    private AlbumResponse response;
    private Long id;
    private UserDomain userDomain;
    private AlbumRequest request;
    private Long userId;
    private AlbumDomain domainToSave;

    @BeforeEach
    public void setup(){

        domain = AlbumObjectMother.domain();
        response = AlbumObjectMother.response();
        id = 123l;
        userId = 12l;
        userDomain = UserObjectMother.domain();
        request = AlbumObjectMother.request();
        domainToSave = AlbumObjectMother.domainToSave();
    }
    @Test
    public void shouldListAll(){
        when(albumRepository.findAll()).thenReturn(List.of(domain));

        List<AlbumResponse> res = target.listAll();

        assert (res.equals(List.of(response)));
        verify(albumRepository).findAll();
    }
    @Test
    public void shouldGetById(){
        when(albumRepository.getById(id)).thenReturn(domain);

        AlbumResponse res = target.getById(id);

        assert (res.equals(response));
        verify(albumRepository).getById(id);
    }
    @Test
    public void shouldCreate(){
        when(albumRepository.save(domainToSave)).thenReturn(domain);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userDomain));

        AlbumResponse res = target.create(request);

        assert (res.equals(response));
        verify(albumRepository).save(domainToSave);
        verify(userRepository).findById(userId);
    }
    @Test
    public void shouldDelete(){
        target.deleteById(id);
        verify(albumRepository).deleteById(id);
    }
    @Test
    public void shouldUpdate(){
        domainToSave.setUser(null);
        when(albumRepository.save(domain)).thenReturn(domain);

        AlbumResponse res = target.update(request, id);

        assert (res.equals(response));
        verify(albumRepository).save(domain);
    }
}

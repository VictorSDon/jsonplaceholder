package com.donato.jsonplaceholder.service;
import com.donato.jsonplaceholder.model.album.AlbumDomain;
import com.donato.jsonplaceholder.model.album.AlbumObjectMother;
import com.donato.jsonplaceholder.model.photo.PhotoDomain;
import com.donato.jsonplaceholder.model.photo.PhotoObjectMother;
import com.donato.jsonplaceholder.model.photo.PhotoRequest;
import com.donato.jsonplaceholder.model.photo.PhotoResponse;
import com.donato.jsonplaceholder.repository.jpa.AlbumRepository;
import com.donato.jsonplaceholder.repository.jpa.PhotoRepository;
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
public class PhotoServiceTest {
    @Mock
    private PhotoRepository photoRepository;
    @Mock
    private AlbumRepository albumRepository;
    private Long id;
    private Long albumId;
    private AlbumDomain albumDomain;
    private PhotoResponse response;
    private PhotoDomain domain;
    private PhotoDomain domainToSave;
    private PhotoRequest request;
    @InjectMocks
    private PhotoService target;

    @BeforeEach
    public void setup(){
        domain = PhotoObjectMother.domain();
        response = PhotoObjectMother.response();
        id = 123L;
        albumId = 12L;
        albumDomain = AlbumObjectMother.albumDomain();
        request = PhotoObjectMother.request();
        domainToSave = PhotoObjectMother.domainToSave();
    }
    @Test
    public void shouldListAll(){
        when(photoRepository.findAll()).thenReturn(List.of(domain));

        List<PhotoResponse> res = target.listAll();

        assert (res.equals(List.of(response)));
        verify(photoRepository).findAll();
    }
    @Test
    public void shouldGetById(){
        when(photoRepository.getById(id)).thenReturn(domain);

        PhotoResponse res = target.getById(id);

        assert (res.equals(response));
        verify(photoRepository).getById(id);
    }
    @Test
    public void shouldCreate(){
        when(photoRepository.save(domainToSave)).thenReturn(domain);

        when(albumRepository.findById(albumId)).thenReturn(Optional.of(albumDomain));

        PhotoResponse res = target.create(request);

        assert (res.equals(response));
        verify(photoRepository).save(domainToSave);
        verify(albumRepository).findById(albumId);
    }
    @Test
    public void shouldDelete(){
        target.deleteById(id);
        verify(photoRepository).deleteById(id);
    }
    @Test
    public void shouldUpdate(){
        when(photoRepository.save(domain)).thenReturn(domain);

        PhotoResponse res = target.update(request, id);

        assert (res.equals(response));
        verify(photoRepository).save(domain);
    }
}

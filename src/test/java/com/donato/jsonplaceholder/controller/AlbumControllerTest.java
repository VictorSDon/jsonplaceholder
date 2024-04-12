package com.donato.jsonplaceholder.controller;


import com.donato.jsonplaceholder.model.album.AlbumObjectMother;
import com.donato.jsonplaceholder.model.album.AlbumRequest;
import com.donato.jsonplaceholder.model.album.AlbumResponse;
import com.donato.jsonplaceholder.service.AlbumService;
import com.donato.jsonplaceholder.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(AlbumController.class)
public class AlbumControllerTest {
    @MockBean
//  quando for chamado pelo requiredargs 'private finals' ele sobrescreve por um mock de
//  modo que possa ser verificado o comportamento da função
    private AlbumService albumService;
    @Autowired
//  Atribui instancia de classe ao MockMVC
    private MockMvc mockMvc;

    private AlbumResponse response;

    private AlbumRequest request;

    @BeforeEach
    public void setup(){
        response = AlbumObjectMother.response();
        request = AlbumObjectMother.request();
    }
    @Test
    public void shouldListAll() throws Exception{
        mockMvc.perform(get("/album")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(albumService).listAll();
    }
    @Test
    public void shouldGetById() throws Exception{
        mockMvc.perform(get("/album/1")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(albumService).getById(1L);
    }
    @Test
    public void shouldCreate() throws Exception{
        mockMvc.perform(post("/album")
                .accept(MediaType.ALL).content(TestUtils.asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(albumService).create(request);
    }
    @Test
    public void shouldDelete() throws Exception{
        mockMvc.perform(delete("/album/1")
                .accept(MediaType.ALL))
                .andExpect(status().isNoContent());
        verify(albumService).deleteById(1L);
    }
    @Test
    public void shouldUpdate() throws Exception{
        mockMvc.perform(put("/album/1")
                .accept(MediaType.ALL).content(TestUtils.asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(albumService).update(request, 1L);
    }
}

package com.donato.jsonplaceholder.controller;

import com.donato.jsonplaceholder.model.photo.PhotoObjectMother;
import com.donato.jsonplaceholder.model.photo.PhotoRequest;
import com.donato.jsonplaceholder.service.PhotoService;
import com.donato.jsonplaceholder.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
@WebMvcTest(PhotoController.class)
public class PhotoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PhotoService service;

    private PhotoRequest request;

    @BeforeEach
    public void setup(){
        request = PhotoObjectMother.request();
    }

    @Test
    public void shouldListAll() throws Exception{
        mockMvc.perform(get("/photo")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service).listAll();
    }
    @Test
    public void shouldGetById() throws Exception{
        mockMvc.perform(get("/photo/1")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service).getById(1L);
    }
    @Test
    public void shouldCreate() throws Exception{
        mockMvc.perform(post("/photo")
                .accept(MediaType.ALL)
                .content(TestUtils.asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(service).create(request);
    }
    @Test
    public void shouldDelete() throws Exception{
        mockMvc.perform(delete("/photo/1")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(service).deleteById(1L);
    }
    @Test
    public void shouldUpdate() throws Exception{
        mockMvc.perform(put("/photo/1")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(request)))
                .andExpect(status().isOk());
        verify(service).update(request, 1L);
    }
}

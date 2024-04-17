package com.donato.jsonplaceholder.controller;

import com.donato.jsonplaceholder.model.photo.PhotoObjectMother;
import com.donato.jsonplaceholder.model.post.PostObjectMother;
import com.donato.jsonplaceholder.model.post.PostRequest;
import com.donato.jsonplaceholder.service.PostService;
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
@WebMvcTest(PostController.class)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostService service;

    private PostRequest request;

    @BeforeEach
    public void setup(){
        request = PostObjectMother.request();
    }

    @Test
    public void shouldListAll() throws Exception{
        mockMvc.perform(get("/post")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service).listAll();
    }
    @Test
    public void shouldGetById() throws Exception{
        mockMvc.perform(get("/post/1")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service).getById(1L);
    }
    @Test
    public void shouldCreate() throws Exception{
        mockMvc.perform(post("/post")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(request)))
                .andExpect(status().isCreated());
        verify(service).create(request);
    }
    @Test
    public void shouldDelete() throws Exception{
        mockMvc.perform(delete("/post/1")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(service).deleteById(1L);
    }
    @Test
    public void shouldUpdate() throws Exception{
        mockMvc.perform(put("/post/1")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(request)))
                .andExpect(status().isOk());
        verify(service).update(request, 1L);
    }
}

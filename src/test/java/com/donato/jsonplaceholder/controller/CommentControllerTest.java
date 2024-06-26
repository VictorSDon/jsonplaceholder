package com.donato.jsonplaceholder.controller;

import com.donato.jsonplaceholder.model.comment.CommentObjectMother;
import com.donato.jsonplaceholder.model.comment.CommentRequest;
import com.donato.jsonplaceholder.service.CommentService;
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
@WebMvcTest(CommentController.class)
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentService service;

    private CommentRequest request;



    @BeforeEach
    public void setup(){
        request = CommentObjectMother.request();
    }
    @Test
    public void shouldListAll() throws Exception{
        mockMvc.perform(get("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
        verify(service).listAll();
    }
    @Test
    public void shouldGetById() throws Exception{
        mockMvc.perform(get("/comment/1")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service).getById(1L);
    }
    @Test
    public void shouldCreate() throws Exception{
        mockMvc.perform(post("/comment")
                .accept(MediaType.ALL).content(TestUtils.asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(service).create(request);
    }
    @Test
    public void shouldDelete() throws Exception{
        mockMvc.perform(delete("/comment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andExpect(status().isNoContent());
        verify(service).deleteById(1L);
    }
    @Test
    public void shouldUpdate() throws Exception{
        mockMvc.perform(put("/comment/1")
                .accept(MediaType.ALL)
                .content(TestUtils.asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service).update(request, 1L);
    }
}

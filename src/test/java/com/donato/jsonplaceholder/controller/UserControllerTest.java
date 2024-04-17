package com.donato.jsonplaceholder.controller;

import com.donato.jsonplaceholder.model.user.UserObjectMother;
import com.donato.jsonplaceholder.model.user.request.UserRequest;
import com.donato.jsonplaceholder.service.UserService;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService service;

    private UserRequest request;

    @BeforeEach
    public void setup(){
        request = UserObjectMother.request();
    }
    @Test
    public void shouldListAll() throws Exception{
        mockMvc.perform(get("/user")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service).listAll();
    }
    @Test
    public void shouldGetById() throws Exception{
        mockMvc.perform(get("/user/1")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service).getById(1L);
    }
    @Test
    public void shouldCreate() throws Exception{
        mockMvc.perform(post("/user")
                .accept(MediaType.ALL)
                .content(TestUtils.asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(service).create(request);
    }
    @Test
    public void shouldDelete() throws Exception{
        mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)).andExpect(status()
                .isNoContent());
        verify(service).deleteById(1L);
    }
    @Test
    public void shouldUpdate() throws Exception{
        mockMvc.perform(put("/user/1")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(request)))
                .andExpect(status().isOk());
        verify(service).update(request, 1L);
    }
}

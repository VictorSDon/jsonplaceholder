package com.donato.jsonplaceholder.controller;

import com.donato.jsonplaceholder.model.todo.TodoObjectMother;
import com.donato.jsonplaceholder.model.todo.TodoRequest;
import com.donato.jsonplaceholder.service.TodoService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TodoService todoService;
    private TodoRequest todoRequest;
    @BeforeEach
    private void setup(){
        todoRequest = TodoObjectMother.request();
    }
    @Test
    public void shouldListAll() throws Exception {
        mockMvc.perform(get("/todo")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(todoService).listAll();
    }
    @Test
    public void shouldGetById() throws Exception {
        mockMvc.perform(get("/todo/1")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(todoService).getById(1L);
    }
    @Test
    public void shouldCreate() throws Exception {
        mockMvc.perform(post("/todo")
                .accept(MediaType.ALL).content(TestUtils.asJsonString(todoRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(todoService).create(todoRequest);
    }
    @Test
    public void shouldDelete() throws Exception {
        mockMvc.perform(delete("/todo/1")
                .accept(MediaType.ALL)).andExpect(status().isNoContent());
        verify(todoService).deleteById(1L);
    }
    @Test
    public void shouldUpdate() throws Exception {
        mockMvc.perform(put("/todo/1")
                        .accept(MediaType.ALL)
                        .content(TestUtils.asJsonString(todoRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(todoService).update(todoRequest, 1L);
    }
}


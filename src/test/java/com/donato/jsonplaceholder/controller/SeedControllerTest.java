package com.donato.jsonplaceholder.controller;

import com.donato.jsonplaceholder.service.SeedService;
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
@WebMvcTest(SeedController.class)
public class SeedControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SeedService service;

    @Test
    public void shouldFullSeed() throws Exception{
        mockMvc.perform(post("/seed/full")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andExpect(status().isNoContent());
        verify(service).fullSeed();
    }
}

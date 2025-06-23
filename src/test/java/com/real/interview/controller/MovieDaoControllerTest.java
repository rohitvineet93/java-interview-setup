package com.real.interview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.real.interview.model.dao.MovieDao;
import com.real.interview.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieDaoControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private MovieRepository movieRepository;
    @Autowired private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() throws Exception {
        MovieDao movieDao = new MovieDao(12134L, "A", 2013);
        mockMvc.perform(post("/movies").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieDao))).andExpect(status().isOk());
    }

    @Test
    void get() {
    }

    @Test
    void list() {
    }

    @Test
    void updateMovie() {
    }

    @Test
    void deleteMovie() {
    }

    @Test
    void search() {
    }
}
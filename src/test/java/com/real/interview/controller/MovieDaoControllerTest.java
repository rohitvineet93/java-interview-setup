package com.real.interview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.real.interview.model.dao.MovieDao; // It's better to use MovieDto for requests in tests
import com.real.interview.model.dto.MovieDto; // Import MovieDto
import com.real.interview.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieDaoControllerTest { // Consider renaming to MovieControllerTest as it tests the controller, not the DAO directly
    @Autowired private MockMvc mockMvc;
    @Autowired private MovieRepository movieRepository;
    @Autowired private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        movieRepository.deleteAll();
    }
    @AfterEach
    void tearDown() {
    }

    @Test
    void createMovie_shouldReturnCreatedMovieAnd201Status() throws Exception {
        MovieDao newMovieRequest = new MovieDao(2392L,  "movie1", 2023, "test_user", LocalDateTime.now());

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newMovieRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("movie1"))
                .andExpect(jsonPath("$.releaseYear").value(2023));
    }
}
package com.real.interview.util;

import com.real.interview.model.dao.MovieDao;
import com.real.interview.model.dto.MovieDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MovieConverter {

    public MovieDto convertToDto(MovieDao movieDao) {
        if (movieDao == null) {
            return null;
        }
        MovieDto movieDto = new MovieDto(movieDao.getId(), movieDao.getTitle(), movieDao.getReleaseYear());
        // Map other fields as needed
        return movieDto;
    }
}
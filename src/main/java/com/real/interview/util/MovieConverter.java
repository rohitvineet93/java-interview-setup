package com.real.interview.util;

import com.real.interview.model.dao.MovieDao;
import com.real.interview.model.dto.MovieDto;
import org.springframework.stereotype.Component;
@Component
public class MovieConverter {

    public MovieDto convertToDto(MovieDao movieDao) {
        if (movieDao == null) {
            return null;
        }
       return new MovieDto(movieDao.getId(), movieDao.getTitle(), movieDao.getReleaseYear());
    }
}
package com.real.interview.util;

import com.real.interview.model.dao.MovieDao;
import com.real.interview.model.dto.MovieDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime; // Important to import if using LocalDateTime
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieConverter {
    public MovieDto convertToDto(MovieDao movieDao) {
        if (movieDao == null) {
            return null;
        }
        return new MovieDto(
                movieDao.getId(),
                movieDao.getTitle(),
                movieDao.getReleaseYear()
        );
    }
    public MovieDao toDao(MovieDto movieDto) {
        if (movieDto == null) {
            return null;
        }

        MovieDao movieDao = new MovieDao();
        movieDao.setTitle(movieDto.getTitle());
        movieDao.setReleaseYear(movieDto.getReleaseYear());
        movieDao.setCreatedBy("user");
        movieDao.setCreatedAt(LocalDateTime.now());

        return movieDao;
    }

    public List<MovieDto> convertToDtoList(List<MovieDao> movieDaos) {
        if (movieDaos == null) {
            return Collections.emptyList();
        }
        return movieDaos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDao> toDaoList(List<MovieDto> movieDtos) {
        if (movieDtos == null) {
            return Collections.emptyList();
        }
        return movieDtos.stream()
                .map(this::toDao)
                .collect(Collectors.toList());
    }
}
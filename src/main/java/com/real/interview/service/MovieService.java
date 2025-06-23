package com.real.interview.service;

import com.real.interview.model.dao.MovieDao;
import com.real.interview.model.dto.MovieDto;
import com.real.interview.repository.MovieRepository;
import com.real.interview.util.MovieConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieConverter movieConverter;

    public MovieService(MovieRepository movieRepository, MovieConverter movieConverter) {
        this.movieRepository = movieRepository;
        this.movieConverter = movieConverter;
    }

    public MovieDto createMovie(MovieDao movieDao) {
        MovieDao savedMovie = movieRepository.save(movieDao);
        return movieConverter.convertToDto(savedMovie);
    }

    public Optional<MovieDto> getMovie(Long id) {
        return movieRepository.findById(id)
                .map(movieConverter::convertToDto);
    }

    public List<MovieDto> getAllMovie() {
        List<MovieDao> movieDaos = movieRepository.findAll();
        return movieDaos.stream()
                .map(movieConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public MovieDto updateMovie(Long id, MovieDto movieDto) {
        return movieRepository.findById(id).map(m -> {
            m.setTitle(movieDto.getTitle());
            m.setReleaseYear(movieDto.getReleaseYear());
            movieRepository.save(m);
            return movieDto;
        }).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found with ID: " + id);
        }
        movieRepository.deleteById(id);
    }

    public List<MovieDto> searchByTitle(String title) {
        List<MovieDao> movieDaos = movieRepository.findByTitleContainingIgnoreCase(title);
        return movieDaos.stream()
                .map(movieConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> searchByReleaseYear(Integer releaseYear) {
        List<MovieDao> movieDaos = movieRepository.findByReleaseYear(releaseYear);
        return movieDaos.stream()
                .map(movieConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
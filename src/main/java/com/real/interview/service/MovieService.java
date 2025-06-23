package com.real.interview.service;

import com.real.interview.exception.MovieNotFoundException;
import com.real.interview.model.dao.MovieDao;
import com.real.interview.model.dto.MovieDto;
import com.real.interview.repository.MovieRepository;
import com.real.interview.util.MovieConverter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime; // Make sure this is imported if used in MovieDao/Dto
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

    public MovieDto createMovie(MovieDto movieDto) {
        MovieDao movieDao = movieConverter.toDao(movieDto); // Convert DTO to DAO
        movieDao.setCreatedAt(LocalDateTime.now()); // Set creation timestamp here or in DAO lifecycle
        MovieDao savedMovie = movieRepository.save(movieDao);
        return movieConverter.convertToDto(savedMovie);
    }

    public Optional<MovieDto> getMovie(Long id) {
        return movieRepository.findById(id)
                .map(movieConverter::convertToDto);
    }
    public List<MovieDto> getAllMovie() {
        List<MovieDao> movieDaos = movieRepository.findAll();
        return movieConverter.convertToDtoList(movieDaos);
    }

    public MovieDto updateMovie(Long id, MovieDto movieDto) {
        return movieRepository.findById(id)
                .map(existingMovieDao -> {
                    existingMovieDao.setTitle(movieDto.getTitle());
                    existingMovieDao.setReleaseYear(movieDto.getReleaseYear());
                    // Update other fields as needed from movieDto to existingMovieDao

                    MovieDao updatedMovieDao = movieRepository.save(existingMovieDao);
                    return movieConverter.convertToDto(updatedMovieDao);
                })
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + id)); // <--- UPDATED EXCEPTION
    }

    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException("Movie not found with ID: " + id); // <--- UPDATED EXCEPTION
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
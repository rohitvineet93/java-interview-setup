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

    // Constructor Injection
    public MovieService(MovieRepository movieRepository, MovieConverter movieConverter) {
        this.movieRepository = movieRepository;
        this.movieConverter = movieConverter;
    }

    public MovieDto createMovie(MovieDao movieDao) {
        MovieDao savedMovie = movieRepository.save(movieDao);
        return movieConverter.convertToDto(savedMovie); // Using the converter
    }

    public Optional<MovieDto> getMovie(Long id) {
        // Find the DAO, then map it to DTO using the converter
        return movieRepository.findById(id)
                .map(movieConverter::convertToDto); // Concise way to use Optional.map with a method reference
    }

    public List<MovieDto> getAllMovie() {
        List<MovieDao> movieDaos = movieRepository.findAll();
        // Convert the list of DAOs to a list of DTOs using stream and converter
        return movieDaos.stream()
                .map(movieConverter::convertToDto) // Using the converter
                .collect(Collectors.toList());
    }

    public MovieDto updateMovie(Long id, MovieDto movieDto) {
        return movieRepository.findById(id).map(m -> {
            m.setTitle(movieDto.getTitle()); // <-- Error points here (or a similar line)
            m.setReleaseYear(movieDto.getReleaseYear());
            movieRepository.save(m);
            return movieDto;
        }).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public void deleteMovie(Long id) {
        // Optional: Check if movie exists before deleting
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found with ID: " + id);
        }
        movieRepository.deleteById(id);
    }

    // Change return type to List<MovieDto> and use the converter
    public List<MovieDto> searchByTitle(String title) {
        List<MovieDao> movieDaos = movieRepository.findByTitleContainingIgnoreCase(title);
        return movieDaos.stream()
                .map(movieConverter::convertToDto)
                .collect(Collectors.toList());
    }

    // Change return type to List<MovieDto> and use the converter
    public List<MovieDto> searchByReleaseYear(Integer releaseYear) {
        List<MovieDao> movieDaos = movieRepository.findByReleaseYear(releaseYear);
        return movieDaos.stream()
                .map(movieConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
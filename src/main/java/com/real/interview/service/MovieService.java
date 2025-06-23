package com.real.interview.service;

import com.real.interview.model.dao.MovieDao;
import com.real.interview.model.dto.MovieDto;
import com.real.interview.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public MovieDao createMovie(MovieDao movieDao) {
        return movieRepository.save(movieDao);
    }
    public Optional<MovieDao> getMovie(Long id) {
        return movieRepository.findById(id);
    }
    public List<MovieDao> getAllMovie() {
        return movieRepository.findAll();
    }
    public MovieDao updateMovie(Long id, MovieDao movieDto) {
        return movieRepository.findById(id).map(m -> {
            m.setTitle(movieDto.getTitle());
            m.setReleaseYear(movieDto.getReleaseYear());
            return movieRepository.save(m);
        }).orElseThrow(() -> new RuntimeException("Movie not found"));
    }
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
    public List<MovieDao> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }
    public List<MovieDao> searchByReleaseYear(Integer releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }
}

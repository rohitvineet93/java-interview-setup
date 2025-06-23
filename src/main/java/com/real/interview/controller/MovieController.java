package com.real.interview.controller;

import com.real.interview.model.dao.MovieDao;
import com.real.interview.model.dto.MovieDto;
import com.real.interview.service.MovieService;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieDto> create(@RequestBody MovieDao movieDao) {
        MovieDto createdMovie = movieService.createMovie(movieDao);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }
    @GetMapping
    public ResponseEntity<MovieDto> get(@PathVariable Long id) {
        return  movieService.getMovie(id).map((ResponseEntity::ok)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> list() {
        return ResponseEntity.ok(movieService.getAllMovie());
    }

    @PutMapping
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        return  ResponseEntity.ok(movieService.updateMovie(id, movieDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDto>> search(@RequestParam(required = false) String title, @RequestParam(required = false) Integer year) {
        if(title != null) {
            return ResponseEntity.ok(movieService.searchByTitle(title));
        } else if (year != null) {
            return ResponseEntity.ok(movieService.searchByReleaseYear(year));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

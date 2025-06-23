package com.real.interview.controller;

import com.real.interview.model.dao.MovieDao;
import com.real.interview.model.dto.MovieDto;
import com.real.interview.service.MovieService;
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
    public ResponseEntity<MovieDao> create(@RequestBody MovieDao movieDao) {
        return ResponseEntity.ok(movieService.createMovie(movieDao));
    }

    @GetMapping
    public ResponseEntity<MovieDao> get(@PathVariable Long id) {
        return  movieService.getMovie(id).map((ResponseEntity::ok)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MovieDao>> list() {
        return ResponseEntity.ok(movieService.getAllMovie());
    }

    @PutMapping
    public ResponseEntity<MovieDao> updateMovie(@PathVariable Long id, @RequestBody MovieDao movieDto) {
        return  ResponseEntity.ok(movieService.updateMovie(id, movieDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDao>> search(@RequestParam(required = false) String title, @RequestParam(required = false) Integer year) {
        if(title != null) {
            return ResponseEntity.ok(movieService.searchByTitle(title));
        } else if (year != null) {
            return ResponseEntity.ok(movieService.searchByReleaseYear(year));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

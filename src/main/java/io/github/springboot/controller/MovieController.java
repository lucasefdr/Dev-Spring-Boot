package io.github.springboot.controller;

import io.github.springboot.domain.Movie;
import io.github.springboot.dto.MovieDTO;
import io.github.springboot.service.MovieService;
import io.github.springboot.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movies") // localhost:8080/movies/
@Log4j2
@RequiredArgsConstructor
public class MovieController {
    private final DateUtil dateUtil; // Dependency Injection com o @RequiredArgsConstructor
    private final MovieService movieService; // Dependency Injection com o @RequiredArgsConstructor

    @GetMapping
    public ResponseEntity<List<Movie>> listMovies() {
        log.info("Log4j2 of DateUtil: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(movieService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody MovieDTO movieDTO) {
        Movie newMovie = movieService.save(movieDTO);
        return ResponseEntity.created(URI.create("/movies/" + newMovie.getId())).body(newMovie);
        // return new ResponseEntity<>(movieService.save(movie), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody MovieDTO movieDTO) {
        movieService.replace(movieDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.github.springboot.controller;

import com.github.springboot.dto.MovieDTO;
import com.github.springboot.util.DateUtil;
import com.github.springboot.domain.Movie;
import com.github.springboot.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("movies") // localhost:8080/movies/
@Log4j2
@RequiredArgsConstructor
public class MovieController {
    private final DateUtil dateUtil; // Dependency Injection com o @RequiredArgsConstructor
    private final MovieService movieService; // Dependency Injection com o @RequiredArgsConstructor

    @GetMapping
    public ResponseEntity<Page<Movie>> listMovies(Pageable pageable) {
        log.info("Log4j2 of DateUtil: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(movieService.listAll(pageable));
    }

//    @GetMapping
//    public ResponseEntity<List<Movie>> listMovies() {
//        log.info("Log4j2 of DateUtil: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
//        return ResponseEntity.ok(movieService.listAll());
//    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @GetMapping(path = "/findBy")
    public ResponseEntity<List<Movie>> findByName(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(movieService.findByName(name));
    }

    @GetMapping(path = "/findByContains")
    public ResponseEntity<List<Movie>> findByNameContains(@RequestParam(required = false) String text) {
        return ResponseEntity.ok(movieService.findByNameContais(text));
    }

    @PostMapping
    public ResponseEntity<Movie> save(@Valid @RequestBody MovieDTO movieDTO) {
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
    public ResponseEntity<Void> replace(@Valid @RequestBody MovieDTO movieDTO) {
        movieService.replace(movieDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

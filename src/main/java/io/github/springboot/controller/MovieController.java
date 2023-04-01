package io.github.springboot.controller;

import io.github.springboot.domain.Movie;
import io.github.springboot.service.MovieService;
import io.github.springboot.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("movies") // localhost:8080/movies/
@Log4j2
@RequiredArgsConstructor
public class MovieController {
    @Autowired
    private DateUtil dateUtil; // Dependency Injection com Autowired
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
}

package io.github.springboot.controller;

import io.github.springboot.domain.Movie;
import io.github.springboot.service.MovieService;
import io.github.springboot.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("movies")
@Log4j2
@RequiredArgsConstructor
public class MovieController {
    @Autowired
    private DateUtil dateUtil; // Dependency Injection com Autowired
    private final MovieService movieService; // Dependency Injection com o @RequiredArgsConstructor

    // localhost:8080/movies/
    @GetMapping(path = "list")
    public List<Movie> listMovies() {
        log.info("Log4j2 of DateUtil: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return movieService.listAll();
    }
}

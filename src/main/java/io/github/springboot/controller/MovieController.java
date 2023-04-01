package io.github.springboot.controller;

import io.github.springboot.domain.Movie;
import io.github.springboot.util.DateUtil;
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
public class MovieController {
    // Dependency Injection com Autowired
    @Autowired
    private DateUtil dateUtil;

    // localhost:8080/movies/
    @GetMapping(path = "list")
    public List<Movie> listMovies() {
        log.info("Log4j2 of DateUtil: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return List.of(new Movie("The Lord of the Rings"), new Movie("PS: I Love You"));
    }
}

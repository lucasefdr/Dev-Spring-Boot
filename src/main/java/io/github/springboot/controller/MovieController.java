package io.github.springboot.controller;

import io.github.springboot.domain.Movie;
import io.github.springboot.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    // Dependency Injection com Autowired
    @Autowired
    private DateUtil dateUtil;

    // localhost:8080/movies/
    @GetMapping(path = "list")
    public List<Movie> listMovies() {
        return List.of(new Movie("The Lord of the Rings"), new Movie("PS: I Love You"));
    }
}

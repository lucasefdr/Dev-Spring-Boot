package io.github.springboot.service;

import io.github.springboot.domain.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MovieService {
    private final List<Movie> movies = List.of(
            new Movie(1L, "The Lord of the Rings"),
            new Movie(2L, "PS: I Love You"),
            new Movie(3L, "Revengers"));

    public List<Movie> listAll() {
        return movies;
    }

    public Movie findById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie not found"));
    }
}

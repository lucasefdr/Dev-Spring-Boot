package io.github.springboot.service;

import io.github.springboot.domain.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MovieService {
    private static List<Movie> movies;

    static {
        movies = new ArrayList<>(List.of(new Movie(1L, "The Lord of the Rings"), new Movie(2L, "PS: I Love You"), new Movie(3L, "Revengers")));
    }

    public List<Movie> listAll() {
        return movies;
    }

    public Movie findById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie not found"));
    }

    public Movie save(Movie movie) {
        movie.setId(ThreadLocalRandom.current().nextLong(3, 1000000));
        movies.add(movie);
        return movie;
    }

    public void delete(Long id) {
        movies.remove(findById(id));
    }

    public void replace(Movie movie) {
        delete(movie.getId());
        movies.add(movie);
    }
}

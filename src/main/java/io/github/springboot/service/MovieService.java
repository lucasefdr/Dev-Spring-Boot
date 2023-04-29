package io.github.springboot.service;

import io.github.springboot.domain.Movie;
import io.github.springboot.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MovieService {
    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> listAll() {
        return repository.findAll();
    }

    public Movie findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie not found"));
    }

    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void replace(Movie movie) {
        findById(movie.getId());
        repository.save(movie);
    }
}

package io.github.springboot.service;

import io.github.springboot.domain.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    public List<Movie> listAll() {
        return List.of(
                new Movie(1L, "The Lord of the Rings"),
                new Movie(2L, "PS: I Love You"),
                new Movie(3L, "Revengers"));
    }
}

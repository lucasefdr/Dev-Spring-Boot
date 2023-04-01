package io.github.springboot.repository;

import io.github.springboot.domain.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> listAll();
}

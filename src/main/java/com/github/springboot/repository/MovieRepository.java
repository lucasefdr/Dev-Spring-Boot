package com.github.springboot.repository;

import com.github.springboot.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByName(String name);
    List<Movie> findByNameContains(String text);
}

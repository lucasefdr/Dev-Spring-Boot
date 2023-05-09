package com.github.springboot.service;

import com.github.springboot.domain.Movie;
import com.github.springboot.dto.MovieDTO;
import com.github.springboot.exception.BadRequestException;
import com.github.springboot.mapper.MovieMapper;
import com.github.springboot.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
                .orElseThrow(() -> new BadRequestException("Movie not found")); // Implementa exceção customizada
    }

    public List<Movie> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Movie> findByNameContais(String text) {
        return repository.findByNameContains(text);
    }

    public Movie save(MovieDTO movieDTO) {
        return repository.save(MovieMapper.INSTANCE.toMovie(movieDTO));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void replace(MovieDTO movieDTO) {
        findById(movieDTO.getId());
        repository.save(MovieMapper.INSTANCE.toMovie(movieDTO));
    }
}

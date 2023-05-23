package com.github.springboot.service;

import com.github.springboot.domain.Movie;
import com.github.springboot.dto.MovieDTO;
import com.github.springboot.exception.BadRequestException;
import com.github.springboot.mapper.MovieMapper;
import com.github.springboot.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    // Retorna uma página de Movies
    public Page<Movie> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Movie findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BadRequestException("Movie not found")); // Implementa exceção customizada
    }

    public List<Movie> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Movie> findByNameContais(String text) {
        return repository.findByNameContains(text);
    }

    @Transactional(rollbackFor = Exception.class)
    public Movie save(MovieDTO movieDTO) {
        return repository.save(MovieMapper.INSTANCE.toMovie(movieDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void replace(MovieDTO movieDTO) {
        Movie savedMovie = findById(movieDTO.getId());
        Movie movie = MovieMapper.INSTANCE.toMovie(movieDTO);
        movie.setId(savedMovie.getId());
        repository.save(movie);
    }
}

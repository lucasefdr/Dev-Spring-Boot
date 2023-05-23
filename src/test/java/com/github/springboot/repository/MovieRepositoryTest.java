package com.github.springboot.repository;

import com.github.springboot.domain.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@DisplayName("Tests for Movie repository") // Nome para exibição nos testes
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    private Movie createMovie() {
        return Movie.builder().name("Movie name example").build();
    }
    @Test
    @DisplayName("Save creates movie when successful")
    void save_PersistMovie_WhenSuccessful() {
        Movie movieToBeSaved = createMovie();
        Movie savedMovie = this.movieRepository.save(movieToBeSaved);

        Assertions.assertThat(savedMovie).isNotNull(); // O savedMovie não é nulo?
        Assertions.assertThat(savedMovie.getId()).isNotNull(); // O ID do savedMovie não é nulo?
        Assertions.assertThat(savedMovie.getName()).isEqualTo(movieToBeSaved.getName());
    }

    @Test
    void findByNameContains() {
    }
}
package com.github.springboot.repository;

import com.github.springboot.domain.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for Movie repository") // Nome para exibição nos testes
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    private Movie createMovie() {
        return Movie.builder().name("Movie name example").build();
    }

    // Teste para salvamento de uma entidade no banco
    @Test
    @DisplayName("Save persists movie when successful")
    void save_PersistMovie_WhenSuccessful() {
        Movie movieToBeSaved = createMovie();
        Movie savedMovie = this.movieRepository.save(movieToBeSaved);

        Assertions.assertThat(savedMovie).isNotNull(); // O savedMovie não é nulo?
        Assertions.assertThat(savedMovie.getId()).isNotNull(); // O ID do savedMovie não é nulo?
        Assertions.assertThat(savedMovie.getName()).isEqualTo(movieToBeSaved.getName());
    }

    // Teste atualização de uma entidade no banco
    @Test
    @DisplayName("Save updates movie when successful")
    void save_UpdatesMovie_WhenSuccessful() {
        Movie movieToBeSaved = createMovie();
        Movie savedMovie = this.movieRepository.save(movieToBeSaved);
        savedMovie.setName("This is a new name");
        Movie updatedMovie = this.movieRepository.save(savedMovie);

        Assertions.assertThat(updatedMovie).isNotNull(); // O updatedMovie não é nulo?
        Assertions.assertThat(updatedMovie.getId()).isNotNull(); // O ID do updatedMovie não é nulo?
        Assertions.assertThat(updatedMovie.getName()).isEqualTo(savedMovie.getName());
    }

    // Teste para remoção de uma entidade no banco
    @Test
    @DisplayName("Delete removes movie when successful")
    void delete_RemovesMovie_WhenSuccessful() {
        Movie movieToBeSaved = createMovie();
        Movie savedMovie = this.movieRepository.save(movieToBeSaved);

        this.movieRepository.delete(savedMovie);
        Optional<Movie> movieOptional = this.movieRepository.findById(savedMovie.getId());

        Assertions.assertThat(movieOptional).isEmpty();
    }

    // Teste para obter a entidade pelo nome
    @Test
    @DisplayName("Find by name returns a list of movie when successful")
    void findByName_ReturnListOfMovie_WhenSuccessful() {
        Movie movieToBeSaved = createMovie();
        Movie savedMovie = this.movieRepository.save(movieToBeSaved);

        String movieName = savedMovie.getName();
        List<Movie> movies = this.movieRepository.findByName(movieName);

        Assertions.assertThat(movies).isNotEmpty();
        Assertions.assertThat(movies).contains(savedMovie);
    }

    @Test
    @DisplayName("Find by name returns a empty list of movie when no movie is found")
    void findByName_ReturnEmptyList_WhenMovieIsNotFound() {
        List<Movie> movies = this.movieRepository.findByName("example");

        Assertions.assertThat(movies).isEmpty(); // A lista está vazia?
        Assertions.assertThat(movies).isNotNull(); // A lista não é nula?
    }
}
package com.github.springboot.client;

import com.github.springboot.domain.Movie;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        // Obtendo a entidade da requisição
        ResponseEntity<Movie> entity = new RestTemplate().getForEntity("http://localhost:8080/movies/{id}", Movie.class, 3);
        log.info(entity);

        // Obtendo o objeto da requisição
        Movie object = new RestTemplate().getForObject("http://localhost:8080/movies/{id}", Movie.class, 3);
        log.info(object);

        // Obtendo um array de Movies
        Movie[] arrayOfObjects = new RestTemplate().getForObject("http://localhost:8080/movies/all", Movie[].class);
        log.info(Arrays.toString(arrayOfObjects));

        //@formatter:off
        ResponseEntity<List<Movie>> objectsList = new RestTemplate().exchange("http://localhost:8080/movies/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Movie>>() {}); // Classe genérica que retorna um tipo específico
        //@formatter:on
        log.info(objectsList.getBody());

        // Usando o RestTemplate para fazer um POST na nossa API
//        Movie movie = Movie.builder().name("101 Dálmatas").build();
//        Movie moviePOST = new RestTemplate().postForObject("http://localhost:8080/movies", movie, Movie.class);
//        log.info(moviePOST);

        Movie movie = Movie.builder().name("3 é demais").build();
        ResponseEntity<Movie> moviePOST = new RestTemplate().exchange("http://localhost:8080/movies",
                HttpMethod.POST,
                new HttpEntity<>(movie),
                Movie.class);

        log.info(moviePOST);

        Movie movieToBeSaved = moviePOST.getBody();
        assert movieToBeSaved != null;
        movieToBeSaved.setName("6 é demais");
        ResponseEntity<Void> moviePUT = new RestTemplate().exchange("http://localhost:8080/movies",
                HttpMethod.PUT,
                new HttpEntity<>(movieToBeSaved),
                Void.class);

        log.info(moviePUT);

        ResponseEntity<Void> movieDELETE = new RestTemplate().exchange("http://localhost:8080/movies/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                movieToBeSaved.getId());

        log.info(movieDELETE);
    }
}

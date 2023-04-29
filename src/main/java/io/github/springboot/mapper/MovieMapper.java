package io.github.springboot.mapper;

import io.github.springboot.domain.Movie;
import io.github.springboot.dto.MovieDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {
    public static final MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);
    public abstract Movie toMovie(MovieDTO movieDTO);
}

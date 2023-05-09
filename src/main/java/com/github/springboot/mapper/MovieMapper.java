package com.github.springboot.mapper;

import com.github.springboot.dto.MovieDTO;
import com.github.springboot.domain.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {
    public static final MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);
    public abstract Movie toMovie(MovieDTO movieDTO);
}

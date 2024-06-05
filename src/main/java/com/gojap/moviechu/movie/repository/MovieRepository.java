package com.gojap.moviechu.movie.repository;

import com.gojap.moviechu.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

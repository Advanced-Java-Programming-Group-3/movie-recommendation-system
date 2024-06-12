package com.gojap.moviechu.movie.repository;

import com.gojap.moviechu.movie.entity.Genre;
import com.gojap.moviechu.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByOrderByOpeningDateDesc();
    List<Movie> findAllByOrderByScoreDesc();
    List<Movie> findAllByGenre(Genre genre);
}

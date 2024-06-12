package com.gojap.moviechu.movie.dto;

import com.gojap.moviechu.movie.entity.Genre;
import com.gojap.moviechu.movie.entity.Movie;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

import java.time.LocalDate;

@Builder(access = AccessLevel.PRIVATE)
public record MovieDetailGetResponse(
        @NonNull Genre genre,
        @NonNull String description,
        @NonNull String director,
        @NonNull String actors,
        @NonNull LocalDate openingDate,
        int runningTime,
        int audience
) {

    public static MovieDetailGetResponse of(Movie movie) {
        return MovieDetailGetResponse.builder()
                .genre(movie.getGenre())
                .description(movie.getDescription())
                .director(movie.getDirector())
                .actors(movie.getActors())
                .openingDate(movie.getOpeningDate())
                .runningTime(movie.getRunningTime())
                .audience(movie.getAudience())
                .build();
    }
}

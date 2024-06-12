package com.gojap.moviechu.movie.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MovieListGetResponse(
        @NonNull List<MovieGetResponse> movies
) {

    public static MovieListGetResponse of(@NonNull List<MovieGetResponse> movies) {
        return MovieListGetResponse.builder()
                .movies(movies)
                .build();
    }
}

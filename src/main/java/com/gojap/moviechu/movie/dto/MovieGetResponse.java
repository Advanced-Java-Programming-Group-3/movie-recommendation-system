package com.gojap.moviechu.movie.dto;

import com.gojap.moviechu.movie.entity.Movie;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record MovieGetResponse(
        @NonNull String title,
        int score,
        String imageUrl
) {

    public static MovieGetResponse of(Movie movie) {
        return MovieGetResponse.builder()
                .title(movie.getTitle())
                .score(movie.getScore())
                .imageUrl(movie.getImageUrl())
                .build();
    }
}

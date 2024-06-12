package com.gojap.moviechu.member.dto;

import com.gojap.moviechu.movie.entity.Genre;
import lombok.NonNull;

public record GenreCreationRequest(
        @NonNull Genre genre
) {
}

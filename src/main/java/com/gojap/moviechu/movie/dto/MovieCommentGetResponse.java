package com.gojap.moviechu.movie.dto;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MovieCommentGetResponse(
        List<String> messages
) {

    public static MovieCommentGetResponse of(List<String> messages) {
        return MovieCommentGetResponse.builder()
                .messages(messages)
                .build();
    }
}

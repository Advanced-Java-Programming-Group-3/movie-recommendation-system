package com.gojap.moviechu.member.dto;

import lombok.NonNull;

public record CommentScoreCreationRequest(
        int score,
        @NonNull String comment
) {
}

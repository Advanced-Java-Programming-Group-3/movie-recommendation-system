package com.gojap.moviechu.auth.dto;

import com.gojap.moviechu.auth.vo.Token;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record SignInResponse(
        @NonNull String accessToken,
        @NonNull String refreshToken,
        long memberId,
        @NonNull Boolean isExist
) {

    public static SignInResponse of(Token token, long memberId, Boolean isExist) {
        return SignInResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .memberId(memberId)
                .isExist(isExist)
                .build();
    }
}

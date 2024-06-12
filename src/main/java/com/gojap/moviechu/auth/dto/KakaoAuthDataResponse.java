package com.gojap.moviechu.auth.dto;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class KakaoAuthDataResponse {
    @NonNull String access_token;
    @NonNull String token_type;
    @NonNull String refresh_token;
    @NonNull int expires_in;
    @NonNull int refresh_token_expires_in;
}

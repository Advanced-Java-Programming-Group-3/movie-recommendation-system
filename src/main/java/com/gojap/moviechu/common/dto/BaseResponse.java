package com.gojap.moviechu.common.dto;

import lombok.NonNull;

public interface BaseResponse {
    boolean success();
    @NonNull String message();
}

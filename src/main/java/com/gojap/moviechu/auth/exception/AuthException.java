package com.gojap.moviechu.auth.exception;

import com.gojap.moviechu.auth.message.ErrorMessage;
import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public AuthException(ErrorMessage errorMessage) {
        super("[AuthException] : " + errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}

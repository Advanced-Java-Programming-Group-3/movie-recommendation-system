package com.gojap.moviechu.movie.exception;

import com.gojap.moviechu.movie.message.ErrorMessage;
import lombok.Getter;

@Getter
public class MovieException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public MovieException(ErrorMessage errorMessage) {
        super("[MovieException] : " + errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}

package com.gojap.moviechu.common.handler;

import com.gojap.moviechu.auth.exception.AuthException;
import com.gojap.moviechu.common.dto.ErrorResponse;
import com.gojap.moviechu.member.exception.MemberException;
import com.gojap.moviechu.movie.exception.MovieException;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> authException(AuthException exception) {
        val errorMessage = exception.getErrorMessage();
        return ResponseEntity.status(errorMessage.getHttpStatus()).body(ErrorResponse.of(errorMessage.getMessage()));
    }

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ErrorResponse> memberException(MemberException exception) {
        val errorMessage = exception.getErrorMessage();
        return ResponseEntity.status(errorMessage.getHttpStatus()).body(ErrorResponse.of(errorMessage.getMessage()));
    }

    @ExceptionHandler(MovieException.class)
    public ResponseEntity<ErrorResponse> memberException(MovieException exception) {
        val errorMessage = exception.getErrorMessage();
        return ResponseEntity.status(errorMessage.getHttpStatus()).body(ErrorResponse.of(errorMessage.getMessage()));
    }
}

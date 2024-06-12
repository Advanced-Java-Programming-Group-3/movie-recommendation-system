package com.gojap.moviechu.member.exception;

import com.gojap.moviechu.member.message.ErrorMessage;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public MemberException(ErrorMessage errorMessage) {
        super("[MemberException] : " + errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}

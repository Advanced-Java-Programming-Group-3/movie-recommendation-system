package com.gojap.moviechu.member.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_PREFERRED_GENRE("선호 장르 등록 성공"),
    SUCCESS_CREATE_MOVIE_COMMENT("영화 코멘트 등록 성공")
    ;

    private final String message;
}

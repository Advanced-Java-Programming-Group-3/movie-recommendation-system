package com.gojap.moviechu.movie.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_GET_MOVIE("영화 리스트 조회 성공"),
    SUCCESS_GET_RECOMMEND_MOVIE("추천 영화 리스트 조회 성공"),
    SUCCESS_GET_MOVIE_DETAIL("영화 세부 조회 성공"),
    SUCCESS_GET_GENRE_MOVIE("장르별 영화 조회 성공"),
    SUCCESS_GET_MOVIE_COMMENTS("영화 코멘트 리스트 조회 성공"),
    ;

    private final String message;
}

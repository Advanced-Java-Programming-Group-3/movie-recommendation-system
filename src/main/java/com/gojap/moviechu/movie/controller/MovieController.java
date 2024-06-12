package com.gojap.moviechu.movie.controller;

import com.gojap.moviechu.common.dto.SuccessResponse;
import com.gojap.moviechu.movie.dto.MovieCommentGetResponse;
import com.gojap.moviechu.movie.dto.MovieDetailGetResponse;
import com.gojap.moviechu.movie.dto.MovieListGetResponse;
import com.gojap.moviechu.movie.entity.Genre;
import com.gojap.moviechu.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.gojap.moviechu.common.dto.SuccessResponse.success;
import static com.gojap.moviechu.movie.message.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<MovieListGetResponse>>> getMovies() {
        val response = movieService.getMovies();
        return ResponseEntity.ok(success(SUCCESS_GET_MOVIE.getMessage(), response));
    }

    @GetMapping("/recommend")
    public ResponseEntity<SuccessResponse<MovieListGetResponse>> getRecommendMovies(Principal principal) {
        val memberId = Long.valueOf(principal.getName());
        val response = movieService.getRecommendMovies(memberId);
        return ResponseEntity.ok(success(SUCCESS_GET_RECOMMEND_MOVIE.getMessage(), response));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<SuccessResponse<MovieDetailGetResponse>> getMovieDetail(@PathVariable Long movieId) {
        val response = movieService.getMovieDetail(movieId);
        return ResponseEntity.ok(success(SUCCESS_GET_MOVIE_DETAIL.getMessage(), response));
    }

    @GetMapping("/genre")
    public ResponseEntity<SuccessResponse<MovieListGetResponse>> getMovieByGenre(@RequestParam Genre genre) {
        val response = movieService.getMovieByGenreV2(genre);
        return ResponseEntity.ok(success(SUCCESS_GET_GENRE_MOVIE.getMessage(), response));
    }

    @GetMapping("/{movieId}/comment")
    public ResponseEntity<SuccessResponse<MovieCommentGetResponse>> getMovieComments(@PathVariable Long movieId) {
        val response = movieService.getMovieComments(movieId);
        return ResponseEntity.ok(success(SUCCESS_GET_MOVIE_COMMENTS.getMessage(), response));
    }
}

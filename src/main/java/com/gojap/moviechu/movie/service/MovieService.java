package com.gojap.moviechu.movie.service;

import com.gojap.moviechu.manager.entity.MemberMovieManager;
import com.gojap.moviechu.manager.repository.MemberMovieManagerRepository;
import com.gojap.moviechu.member.entity.Member;
import com.gojap.moviechu.member.exception.MemberException;
import com.gojap.moviechu.member.repository.MemberRepository;
import com.gojap.moviechu.movie.dto.MovieCommentGetResponse;
import com.gojap.moviechu.movie.dto.MovieDetailGetResponse;
import com.gojap.moviechu.movie.dto.MovieGetResponse;
import com.gojap.moviechu.movie.dto.MovieListGetResponse;
import com.gojap.moviechu.movie.entity.Genre;
import com.gojap.moviechu.movie.entity.Movie;
import com.gojap.moviechu.movie.exception.MovieException;
import com.gojap.moviechu.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gojap.moviechu.member.message.ErrorMessage.INVALID_MEMBER;
import static com.gojap.moviechu.movie.message.ErrorMessage.INVALID_MOVIE;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;
    private final MemberRepository memberRepository;
    private final MemberMovieManagerRepository memberMovieManagerRepository;

    public List<MovieListGetResponse> getMovies() {
        val recentMovies = getRecentMovies();
        val scoreMovies = getScoreMovies();
        val response = List.of(MovieListGetResponse.of(recentMovies), MovieListGetResponse.of(scoreMovies));
        return response;
    }

    public MovieListGetResponse getRecommendMovies(long memberId) {
        val member = findMember(memberId);
        val recommendMovies = getMovieByGenre(member.getPreferredGenre());
        val response = MovieListGetResponse.of(recommendMovies);
        return response;
    }

    public MovieDetailGetResponse getMovieDetail(long movieId) {
        val movie = findMovie(movieId);
        val response = MovieDetailGetResponse.of(movie);
        return response;
    }

    public MovieListGetResponse getMovieByGenreV2(Genre genre) {
        val movies = getMovieByGenre(genre);
        val response = MovieListGetResponse.of(movies);
        return response;
    }

    public MovieCommentGetResponse getMovieComments(long movieId) {
        findMovie(movieId);
        val comments = getComments(movieId);
        val response = MovieCommentGetResponse.of(comments);
        return response;
    }

    private List<MovieGetResponse> getRecentMovies() {
        return movieRepository.findAllByOrderByOpeningDateDesc()
                .stream()
                .map(MovieGetResponse::of)
                .toList();
    }

    private List<MovieGetResponse> getScoreMovies() {
        return movieRepository.findAllByOrderByScoreDesc()
                .stream()
                .map(MovieGetResponse::of)
                .toList();
    }

    private Member findMember(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private List<MovieGetResponse> getMovieByGenre(Genre genre) {
        return movieRepository.findAllByGenre(genre)
                .stream()
                .map(MovieGetResponse::of)
                .toList();
    }

    private Movie findMovie(long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieException(INVALID_MOVIE));
    }

    private List<String> getComments(long movieId) {
        return memberMovieManagerRepository.findAllByMovieId(movieId)
                .stream()
                .map(MemberMovieManager::getComment)
                .toList();
    }
}

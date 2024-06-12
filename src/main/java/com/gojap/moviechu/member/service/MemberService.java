package com.gojap.moviechu.member.service;

import com.gojap.moviechu.manager.entity.MemberMovieManager;
import com.gojap.moviechu.manager.repository.MemberMovieManagerRepository;
import com.gojap.moviechu.member.dto.CommentScoreCreationRequest;
import com.gojap.moviechu.member.dto.GenreCreationRequest;
import com.gojap.moviechu.member.entity.Member;
import com.gojap.moviechu.member.exception.MemberException;
import com.gojap.moviechu.member.repository.MemberRepository;
import com.gojap.moviechu.movie.entity.Movie;
import com.gojap.moviechu.movie.exception.MovieException;
import com.gojap.moviechu.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.gojap.moviechu.member.message.ErrorMessage.INVALID_MEMBER;
import static com.gojap.moviechu.movie.message.ErrorMessage.INVALID_MOVIE;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MovieRepository movieRepository;
    private final MemberMovieManagerRepository memberMovieManagerRepository;

    @Transactional
    public void createPreferredGenre(long memberId, GenreCreationRequest request) {
        val member = findMember(memberId);
        member.setPreferredGenre(request.genre());
    }

    @Transactional
    public void createMovieCommentAndScore(long memberId, long movieId, CommentScoreCreationRequest request) {
        val member = findMember(memberId);
        val movie = findMovie(movieId);
        saveMemberMovie(member, movie, request);
        movie.setScore(request.score());
    }

    private Member findMember(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private Movie findMovie(long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieException(INVALID_MOVIE));
    }

    private void saveMemberMovie(Member member, Movie movie, CommentScoreCreationRequest request) {
        val memberMovieManager = MemberMovieManager.builder()
                .member(member)
                .movie(movie)
                .score(request.score())
                .comment(request.comment())
                .build();
        memberMovieManagerRepository.save(memberMovieManager);
    }
}

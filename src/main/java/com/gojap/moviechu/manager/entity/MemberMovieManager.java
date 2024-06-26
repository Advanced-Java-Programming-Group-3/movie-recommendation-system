package com.gojap.moviechu.manager.entity;

import com.gojap.moviechu.member.entity.Member;
import com.gojap.moviechu.movie.entity.Movie;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class MemberMovieManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private int score;

    private String comment;

    @Builder
    public MemberMovieManager(Member member, Movie movie, int score, String comment) {
        this.member = member;
        this.movie = movie;
        this.score = score;
        this.comment = comment;
    }
}

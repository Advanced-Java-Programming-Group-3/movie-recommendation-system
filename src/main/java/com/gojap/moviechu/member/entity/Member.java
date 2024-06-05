package com.gojap.moviechu.member.entity;

import com.gojap.moviechu.movie.entity.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String socialId;

    private String refreshToken;

    private Genre preferredGenre;
}

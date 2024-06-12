package com.gojap.moviechu.member.entity;

import com.gojap.moviechu.movie.entity.Genre;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter
    @Enumerated(value = EnumType.STRING)
    private Genre preferredGenre;

    @Builder
    public Member(String socialId) {
        this.socialId = socialId;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}

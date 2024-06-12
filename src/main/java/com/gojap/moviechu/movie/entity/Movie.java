package com.gojap.moviechu.movie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    private String title;

    private String description;

    private int runningTime;

    private String director;

    private String actors;

    private LocalDate openingDate;

    @Setter
    private int score;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private String imageUrl;

    private int audience;
}

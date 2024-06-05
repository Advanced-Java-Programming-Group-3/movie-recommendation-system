package com.gojap.moviechu.movie.controller;

import com.gojap.moviechu.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movies")
public class MovieController {

    private final MovieService movieService;


}

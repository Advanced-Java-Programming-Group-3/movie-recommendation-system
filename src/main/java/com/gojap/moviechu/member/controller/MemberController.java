package com.gojap.moviechu.member.controller;

import com.gojap.moviechu.common.dto.BaseResponse;
import com.gojap.moviechu.member.dto.CommentScoreCreationRequest;
import com.gojap.moviechu.member.dto.GenreCreationRequest;
import com.gojap.moviechu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.gojap.moviechu.common.dto.SuccessResponse.success;
import static com.gojap.moviechu.member.message.SuccessMessage.SUCCESS_CREATE_MOVIE_COMMENT;
import static com.gojap.moviechu.member.message.SuccessMessage.SUCCESS_CREATE_PREFERRED_GENRE;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/genre")
    public ResponseEntity<BaseResponse> createPreferredGenre(
            Principal principal, @RequestBody GenreCreationRequest request
    ) {
        val memberId = Long.valueOf(principal.getName());
        memberService.createPreferredGenre(memberId, request);
        return ResponseEntity.ok(success(SUCCESS_CREATE_PREFERRED_GENRE.getMessage()));
    }

    @PostMapping("/movies/{movieId}/comment")
    public ResponseEntity<BaseResponse> createMovieCommentAndScore(
            Principal principal, @RequestBody CommentScoreCreationRequest request, @PathVariable Long movieId
    ) {
        val memberId = Long.valueOf(principal.getName());
        memberService.createMovieCommentAndScore(memberId, movieId, request);
            return ResponseEntity.ok(success(SUCCESS_CREATE_MOVIE_COMMENT.getMessage()));
    }
}

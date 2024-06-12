package com.gojap.moviechu.auth.controller;

import com.gojap.moviechu.auth.dto.SignInResponse;
import com.gojap.moviechu.auth.service.AuthService;
import com.gojap.moviechu.common.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gojap.moviechu.auth.message.SuccessMessage.SUCCESS_SIGN_IN;
import static com.gojap.moviechu.common.dto.SuccessResponse.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<SuccessResponse<SignInResponse>> signIn(@RequestHeader("Authorization") String code) {
        val response = authService.signIn(code);
        return ResponseEntity.ok(success(SUCCESS_SIGN_IN.getMessage(), response));
    }
}

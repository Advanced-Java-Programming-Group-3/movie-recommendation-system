package com.gojap.moviechu.auth.service;

import com.gojap.moviechu.auth.dto.SignInResponse;
import com.gojap.moviechu.auth.jwt.JwtTokenProvider;
import com.gojap.moviechu.auth.jwt.UserAuthentication;
import com.gojap.moviechu.auth.vo.Token;
import com.gojap.moviechu.member.entity.Member;
import com.gojap.moviechu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoService kakaoService;

    @Value("${jwt.ACCESS_TOKEN_EXPIRED}")
    private Long accessTokenExpired;

    @Value("${jwt.REFRESH_TOKEN_EXPIRED}")
    private Long refreshTokenExpired;

    @Transactional
    public SignInResponse signIn(String code) {
        val socialAccessToken = kakaoService.getToken(code);
        val member = getMember(socialAccessToken);
        val token = getToken(member);
        val isExist = Objects.nonNull(member.getPreferredGenre());
        return SignInResponse.of(token, member.getId(), isExist);
    }

    private Member getMember(String socialAccessToken) {
        val socialId = kakaoService.getSocialId(socialAccessToken);
        return signUp(socialId);
    }

    private Member signUp(String socialId) {
        return memberRepository.findBySocialId(socialId)
                .orElseGet(() -> saveMember(socialId));
    }

    private Member saveMember(String socialId) {
        val member = Member.builder()
                .socialId(socialId)
                .build();
        return memberRepository.save(member);
    }

    private Token getToken(Member member) {
        val token = generateToken(new UserAuthentication(member.getId(), null, null));
        member.updateRefreshToken(token.getRefreshToken());
        return token;
    }

    private Token generateToken(Authentication authentication) {
        return Token.builder()
                .accessToken(jwtTokenProvider.generateToken(authentication, accessTokenExpired))
                .refreshToken(jwtTokenProvider.generateToken(authentication, refreshTokenExpired))
                .build();
    }

}

package com.gojap.moviechu.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gojap.moviechu.auth.dto.KakaoAuthDataResponse;
import com.gojap.moviechu.auth.exception.AuthException;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.gojap.moviechu.auth.message.ErrorMessage.INVALID_TOKEN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KakaoService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${jwt.KAKAO_CLIENT_ID}")
    private String kakaoClientId;

    @Value("${jwt.KAKAO_REDIRECT_URI}")
    private String redirectUri;

    public String getToken(String code) {
        try {
            val headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            val params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", kakaoClientId);
            params.add("code", code);
            params.add("redirect_uri", redirectUri);
            val httpEntity = new HttpEntity<>(params, headers);
            val responseData = restTemplate.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, httpEntity, String.class);
            val objectMapper = new ObjectMapper();
            val response = objectMapper.readValue(responseData.getBody(), KakaoAuthDataResponse.class);
            return response.getAccess_token();
        } catch (Exception exception) {
            throw new AuthException(INVALID_TOKEN);
        }
    }

    public String getSocialId(String socialAccessToken) {
        try {
            val headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + socialAccessToken);
            val httpEntity = new HttpEntity<JsonArray>(headers);
            val responseData = restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", httpEntity, Object.class);
            return objectMapper.convertValue(responseData.getBody(), Map.class).get("id").toString();
        } catch (Exception exception) {
            throw new AuthException(INVALID_TOKEN);
        }
    }
}

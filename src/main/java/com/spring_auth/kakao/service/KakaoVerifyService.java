package com.spring_auth.kakao.service;

import com.spring_auth.kakao.request.KakaoApiResponse;
import com.spring_auth.kakao.request.KakaoUserInfoResponse;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class KakaoVerifyService {

    @Value("${kakao.client_id}")
    private String clientId;

    @Value("${kakao.redirect_url}")
    private String redirectUri;

    private final String KAKAO_AUTH_URL = "https://kauth.kakao.com";
    private final String KAKAO_USER_URL = "https://kapi.kakao.com";

    public KakaoUserInfoResponse getKakaoUserInfo(String accessToeken) {
        return WebClient
                .create(KAKAO_USER_URL)
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToeken)
                .header(HttpHeaders.CONTENT_TYPE,
                        HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoUserInfoResponse.class)
                .retry(3)
                .onErrorResume(ex -> {
                    log.error("카카오 USER API 호출 실패: {}", ex.getMessage());
                    return Mono.just(new KakaoUserInfoResponse());
                })
                .block();
    }

    public String getAccessTokenFromKakao(String code) {
        return WebClient
                .create(KAKAO_AUTH_URL)
                .post()
                .uri(uriBuilder -> uriBuilder
                                            .scheme("https")
                                            .path("/oauth/token")
                                            .queryParam("grant_type", "authorization_code")
                                            .queryParam("client_id", clientId)
                                            .queryParam("redirect_uri", redirectUri)
                                            .queryParam("code", code)
                                            .build())
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8")
                .retrieve()
                .bodyToMono(KakaoApiResponse.class)
                .retry(3)
                .onErrorResume(ex -> {
                    log.error("카카오 API 호출 실패: {}", ex.getMessage());
                    return Mono.just(new KakaoApiResponse()); // fallback 객체 리턴
                })
                .block() // 블로킹 처리
                .getAccessToken();

    }
}

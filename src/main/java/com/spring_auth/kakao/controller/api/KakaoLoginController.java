package com.spring_auth.kakao.controller.api;

import com.spring_auth.kakao.request.KakakoApiRequest;
import com.spring_auth.kakao.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class KakaoLoginController {
    private final KakaoLoginService kakaoLoginService;

    @GetMapping("/callback")
    public ResponseEntity callback(KakakoApiRequest request){
        return ResponseEntity.ok().body(kakaoLoginService.login(request.getCode()));
    }
}

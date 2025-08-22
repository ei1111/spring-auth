package com.spring_auth.kakao.controller.api;

import com.spring_auth.kakao.dto.KakakoApiRequest;
import com.spring_auth.kakao.service.KakaoLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "4. 카카오 로그인 조회" , description = "카카오 로그인 API")
public class KakaoLoginController {
    private final KakaoLoginService kakaoLoginService;

    @GetMapping("/callback")
    @Operation(summary = "로그인시 카카오에 등록된 이름 정보를 가져 올 수 있다.")
    public ResponseEntity callback(KakakoApiRequest request){
        return ResponseEntity.ok().body(kakaoLoginService.login(request.getCode()));
    }
}

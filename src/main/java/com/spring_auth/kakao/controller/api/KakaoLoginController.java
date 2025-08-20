package com.spring_auth.kakao.controller.api;

import com.spring_auth.kakao.request.KakakoApiRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/kakao")
public class KakaoLoginController {

    @GetMapping("/callback")
    public ResponseEntity callback(KakakoApiRequest request){
        log.info("code={}", request.getCode());
        return ResponseEntity.ok().build();
    }
}

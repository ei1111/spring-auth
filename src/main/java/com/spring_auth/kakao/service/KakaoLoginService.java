package com.spring_auth.kakao.service;

import com.spring_auth.employee.repository.EmployeeRepository;
import com.spring_auth.kakao.request.KakaoAccount;
import com.spring_auth.kakao.request.KakaoAccount.Profile;
import com.spring_auth.kakao.request.KakaoUserInfoResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private final KakaoVerifyService kakaoVerifyService;
    private final EmployeeRepository employeeRepository;

    public void login(String code) {
        String accessTokenFromKakao = kakaoVerifyService.getAccessTokenFromKakao(code);
        KakaoUserInfoResponse dto = kakaoVerifyService.getKakaoUserInfo(accessTokenFromKakao);

        String nickname = Optional.ofNullable(dto.getKakaoAccount())
                .map(KakaoAccount::getProfile)
                .map(Profile::getNickname)
                .orElseThrow(() -> new IllegalArgumentException("카카오 사용자 정보 조회 실패"));

        log.info("accessTokenFromKakao={}", nickname);

        if (!employeeRepository.existsEmployeeByKakoNickName(nickname)) {
            throw new IllegalArgumentException("존재하지 않는 임직원 입니다.");
        }
    }
}

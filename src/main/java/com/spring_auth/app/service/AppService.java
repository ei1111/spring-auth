package com.spring_auth.app.service;

import com.spring_auth.app.entity.App;
import com.spring_auth.app.repository.AppRepository;
import com.spring_auth.app.dto.AppRequest;
import com.spring_auth.app.dto.AppResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppService {

    private final AppRepository appRepository;

    public List<AppResponse> findAllApps() {
        return appRepository.findAll().stream()
                .map(App::toResponse)
                .toList();
    }

    @Transactional
    public AppResponse save(AppRequest request) {
        return appRepository.save(request.toAppEntity()).toResponse();
    }
}

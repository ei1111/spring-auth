package com.spring_auth.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {

    private Long apiId;
    private String method;
    private String path;

    @Builder
    public ApiResponse(Long apiId, String method, String path) {
        this.apiId = apiId;
        this.method = method;
        this.path = path;
    }
}

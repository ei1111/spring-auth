package com.spring_auth.app.dto;

import com.spring_auth.api.dto.ApiResponse;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppResponse {
    private Long appId;
    private String systemName;
    private List<ApiResponse> apis;

    @Builder
    public AppResponse(Long appId, String systemName, List<ApiResponse> apis) {
        this.appId = appId;
        this.systemName = systemName;
        this.apis = apis;
    }
}

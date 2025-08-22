package com.spring_auth.app.request;

import com.spring_auth.api.entity.Api;
import com.spring_auth.api.request.ApiResponse;
import com.spring_auth.app.entity.App;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

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

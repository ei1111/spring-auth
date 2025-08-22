package com.spring_auth.appRole.dto;

import com.spring_auth.app.dto.AppResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppRoleResponse {
    private Long appRoleId;
    private AppResponse appResponse;


    @Builder
    public AppRoleResponse(Long appRoleId, AppResponse appResponse) {
        this.appRoleId = appRoleId;
        this.appResponse = appResponse;
    }
}

package com.spring_auth.appRole.request;

import com.spring_auth.app.entity.App;
import com.spring_auth.app.request.AppResponse;
import com.spring_auth.employee.entity.Employee;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppRoleResponse {
    private Long appRoleId;
    private AppResponse appResponse;


    @Builder
    public AppRoleResponse(Long appRoleId, AppResponse appResponse) {
        this.appRoleId = appRoleId;
        this.appResponse = appResponse;
    }
}

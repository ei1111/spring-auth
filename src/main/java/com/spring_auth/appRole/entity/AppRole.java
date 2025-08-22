package com.spring_auth.appRole.entity;

import com.spring_auth.app.entity.App;
import com.spring_auth.appRole.request.AppRoleResponse;
import com.spring_auth.employee.entity.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "app_role")
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appRoleId;

    @JoinColumn(name = "employeeId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee  employee;

    @JoinColumn(name = "appId")
    @ManyToOne(fetch = FetchType.LAZY)
    private App app;


    public AppRoleResponse toResponse() {
        return AppRoleResponse.builder()
                .appRoleId(appRoleId)
                .appResponse(app.toResponse())
                .build();
    }
}

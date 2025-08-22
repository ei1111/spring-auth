package com.spring_auth.employee.dto;

import com.spring_auth.appRole.dto.AppRoleResponse;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpoyeeResponse {
    private Long employeeId;

    private String firstName;

    private String lastName;

    private Long departmentId;

    private Set<String> roles;

    private List<AppRoleResponse> appRoles;

    @Builder
    public EmpoyeeResponse(Long employeeId, String firstName, String lastName, Long departmentId,
            Set<String> roles,List<AppRoleResponse> appRoles ) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.roles = roles;
        this.appRoles = appRoles;
    }
}

package com.spring_auth.role.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleResponse {
    private Long roleId;
    private String roleName;

    @Builder
    public RoleResponse(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}

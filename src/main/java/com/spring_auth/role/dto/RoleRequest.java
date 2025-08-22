package com.spring_auth.role.dto;

import com.spring_auth.role.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
    @Schema(name = "roleName", description = "칙책명", example = "사원")
    private String roleName;

    public Role toRoleEntity() {
        return Role.builder()
                .roleName(roleName)
                .build();
    }
}

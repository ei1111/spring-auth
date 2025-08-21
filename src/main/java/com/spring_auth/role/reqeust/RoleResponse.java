package com.spring_auth.role.reqeust;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

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

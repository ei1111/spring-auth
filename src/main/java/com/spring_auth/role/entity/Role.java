package com.spring_auth.role.entity;

import com.spring_auth.role.reqeust.RoleResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity(name = "role")
public class Role {
    @Id
    @Comment("role pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Comment("부서")
    @Column(length = 45)
    private String roleName;


    @Builder
    public Role(String roleName) {
        this.roleName = roleName;
    }

    public RoleResponse toResponse() {
        return RoleResponse.builder()
                .roleId(this.roleId)
                .roleName(this.roleName)
                .build();
    }
}

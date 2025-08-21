package com.spring_auth.role.entity;

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

    @Comment("인사팀")
    @Column(length = 45)
    private String name;


    @Builder
    public Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }
}

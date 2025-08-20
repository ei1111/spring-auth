package com.spring_auth.role.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity(name = "role")
@Getter
public class Role {
    @Id
    @Comment("role pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Comment("인사팀")
    @Column(length = 45)
    private String name;
}

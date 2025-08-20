package com.spring_auth.department.entity;

import com.spring_auth.department.reqeust.DepartmentResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity(name = "department")
public class Department {
    @Id
    @Comment("부서 pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Comment("부서명")
    @Column(length = 50)
    private String deptName;

    @Builder
    public Department(String deptName) {
        this.deptName = deptName;
    }

    public DepartmentResponse toResponse() {
        return DepartmentResponse.builder()
                .departmentId(this.departmentId)
                .departmentName(this.deptName)
                .build();
    }
}

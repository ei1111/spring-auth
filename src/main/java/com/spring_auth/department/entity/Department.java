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

@Getter
@NoArgsConstructor
@Entity(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(length = 50)
    private String departmentName;

    //private Team teamd

    @Builder
    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public DepartmentResponse toResponse() {
        return DepartmentResponse.builder()
                .departmentId(this.departmentId)
                .departmentName(this.departmentName)
                .build();
    }
}

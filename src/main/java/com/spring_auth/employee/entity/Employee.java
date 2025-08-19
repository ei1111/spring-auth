package com.spring_auth.employee.entity;

import com.spring_auth.department.entity.Department;
import com.spring_auth.employee.reqeust.EmpoyeeResponse;
import jakarta.persistence.Column;
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
@Entity(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(length = 20)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    @JoinColumn(name = "departmentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Department department;


    @Builder
    public Employee(String firstName, String lastName, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public EmpoyeeResponse toResponse() {
        return EmpoyeeResponse.builder()
                .employeeId(this.employeeId)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .departmentId(department.getDepartmentId())
                .build();
    }
}

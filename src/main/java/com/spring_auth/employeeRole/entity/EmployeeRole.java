package com.spring_auth.employeeRole.entity;

import com.spring_auth.employee.entity.Employee;
import com.spring_auth.role.entity.Role;
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
@Entity(name = "employee_Role")
public class EmployeeRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeRoleId;

    @JoinColumn(name = "employeeId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @JoinColumn(name = "roleId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @Builder
    public EmployeeRole(Employee employee, Role role) {
        this.employee = employee;
        this.role = role;
    }
}

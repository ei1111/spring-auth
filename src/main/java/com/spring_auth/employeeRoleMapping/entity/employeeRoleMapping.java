package com.spring_auth.employeeRoleMapping.entity;

import com.spring_auth.employee.entity.Employee;
import com.spring_auth.role.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "employee_role_mapping")
public class employeeRoleMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeRoleMappingId;

    @JoinColumn(name = "employeeId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @JoinColumn(name = "roleId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;
}

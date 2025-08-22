package com.spring_auth.employee.entity;

import com.spring_auth.appRole.entity.AppRole;
import com.spring_auth.department.entity.Department;
import com.spring_auth.employee.dto.EmpoyeeResponse;
import com.spring_auth.employeeRole.entity.EmployeeRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity(name = "employee")
public class Employee {

    @Id
    @Comment("회원 pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Comment("성")
    @Column(length = 20)
    private String firstName;

    @Comment("이름")
    @Column(length = 20)
    private String lastName;

    @Comment("부서 fk")
    @JoinColumn(name = "departmentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Department department;

    @Comment("카카오 인증 이름")
    @Column(unique = true, nullable = false)
    private String kakoNickName;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<EmployeeRole> employeeRoles = new HashSet<>();

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<AppRole> appRoles = new ArrayList<>();

    public void addEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRoles.add(employeeRole);
    }

    @Builder
    public Employee(Long employeeId, String firstName, String lastName, Department department,
            String kakoNickName, Set<EmployeeRole> employeeRoles,  List<AppRole> appRoles) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.kakoNickName = kakoNickName;
        this.employeeRoles = employeeRoles != null ? employeeRoles : new HashSet<>();
        this.appRoles = appRoles != null ? appRoles : new ArrayList<>();
    }

    public EmpoyeeResponse toResponse() {
        return EmpoyeeResponse.builder()
                .employeeId(this.employeeId)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .departmentId(department.getDepartmentId())
                .roles(getRoleNameSet())
                .appRoles(appRoles.stream().map(AppRole::toResponse).toList())
                .build();
    }

    public Set<String> getRoleNameSet() {
        return employeeRoles.stream()
                .map(er -> er.getRole().getRoleName())
                .collect(Collectors.toSet());
    }

    public boolean isHR() {
        return getRoleNameSet().contains("대리");
    }
}

package com.spring_auth.employee.entity;

import com.spring_auth.department.entity.Department;
import com.spring_auth.employee.reqeust.EmpoyeeResponse;
import com.spring_auth.role.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity(name = "employee")
//인사팀이면 유저 등록 기능이 있다
//조직장이면 휴가를 승인할수 있다
//인사팀 조직장이면 둘의 권한을 가질수 있다
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_role_mapping",
            joinColumns = @JoinColumn(name = "employeeId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private Set<Role> roles = new HashSet<>();


    @Builder
    public Employee(Long employeeId, String firstName, String lastName, Department department,
            String kakoNickName, Set<Role> roles) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.kakoNickName = kakoNickName;
        this.roles = roles;
    }

    public EmpoyeeResponse toResponse() {
        return EmpoyeeResponse.builder()
                .employeeId(this.employeeId)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .departmentId(department.getDepartmentId())
                .roles(this.roles)
                .build();
    }

    public List<String> getRoleNameList() {
        return roles.stream()
                .map(Role::getName)
                .toList();
    }
}

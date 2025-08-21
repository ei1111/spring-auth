package com.spring_auth.employee.entity;

import com.spring_auth.department.entity.Department;
import com.spring_auth.employee.reqeust.EmpoyeeResponse;
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
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
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

/*    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_role_mapping",
            joinColumns = @JoinColumn(name = "employeeId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private Set<Role> roles = new HashSet<>();*/

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<EmployeeRole> employeeRoles = new HashSet<>();


    public void addEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRoles.add(employeeRole);
    }

    @Builder
    public Employee(Long employeeId, String firstName, String lastName, Department department,
            String kakoNickName, Set<EmployeeRole> employeeRoles) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.kakoNickName = kakoNickName;
        this.employeeRoles = employeeRoles != null ? employeeRoles : new HashSet<>();
    }

    public EmpoyeeResponse toResponse() {
        return EmpoyeeResponse.builder()
                .employeeId(this.employeeId)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .departmentId(department.getDepartmentId())
                .roles(getRoleNameSet())
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

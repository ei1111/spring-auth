package com.spring_auth.employee.reqeust;

import com.spring_auth.department.entity.Department;
import com.spring_auth.role.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpoyeeResponse {
    private Long employeeId;

    private String firstName;

    private String lastName;

    private Long departmentId;

    private Set<String> roles;

    @Builder
    public EmpoyeeResponse(Long employeeId, String firstName, String lastName, Long departmentId,
            Set<String> roles) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.roles = roles;
    }
}

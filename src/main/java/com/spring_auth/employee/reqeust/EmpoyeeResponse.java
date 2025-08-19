package com.spring_auth.employee.reqeust;

import com.spring_auth.department.entity.Department;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

    @Builder
    public EmpoyeeResponse(Long employeeId, String firstName, String lastName, Long departmentId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
    }
}

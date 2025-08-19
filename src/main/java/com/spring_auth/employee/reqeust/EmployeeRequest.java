package com.spring_auth.employee.reqeust;

import com.spring_auth.department.entity.Department;
import com.spring_auth.employee.entity.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private Long departmentId;

    @Builder
    public EmployeeRequest(String firstName, String lastName, Long departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
    }

    public Employee toEmployeeEntity(Department department) {
        return Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .department(department)
                .build();
    }
}

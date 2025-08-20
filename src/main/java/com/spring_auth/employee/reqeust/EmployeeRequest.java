package com.spring_auth.employee.reqeust;

import com.spring_auth.department.entity.Department;
import com.spring_auth.employee.entity.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeRequest {
    @Schema(name = "firstName", description = "성", example = "홍")
    private String firstName;

    @Schema(name = "lastName", description = "이름", example = "길동")
    private String lastName;

    @Schema(name = "departmentId", description = "부서 id", example = "1")
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

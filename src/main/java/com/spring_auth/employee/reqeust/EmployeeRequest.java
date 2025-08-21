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

    @Schema(name = "deptName", description = "부서명", example = "인사팀")
    private String deptName;

    @Schema(name = "roleName", description = "직책명", example = "사원")
    private String roleName;

    @Schema(name = "kakaoNickName", description = "카카오 닉네임", example = "홍길동")
    private String kakaoNickName;

    @Builder
    public EmployeeRequest(String firstName, String lastName, String deptName, String roleName,
            String kakaoNickName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.deptName = deptName;
        this.roleName = roleName;
        this.kakaoNickName = kakaoNickName;
    }

    public Employee toEmployeeEntity(Department department) {
        return Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .department(department)
                .kakoNickName(kakaoNickName)
                .build();
    }
}

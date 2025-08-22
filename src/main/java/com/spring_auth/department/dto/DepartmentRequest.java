package com.spring_auth.department.dto;

import com.spring_auth.department.entity.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class DepartmentRequest {
    @Schema(name = "deptName", description = "부서명", example = "인사팀")
    private String deptName;

    //private Long teamLeaderId;


    public Department toDepartmentEntity() {
        return Department.builder()
                .deptName(deptName)
                .build();
    }
}

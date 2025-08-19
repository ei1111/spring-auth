package com.spring_auth.department.reqeust;

import com.spring_auth.department.entity.Department;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class DepartmentRequest {
    private String departmentName;

    //private Long teamLeaderId;


    public Department toDepartmentEntity() {
        return Department.builder()
                .departmentName(departmentName)
                .build();
    }
}

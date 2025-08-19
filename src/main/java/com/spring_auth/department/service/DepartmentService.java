package com.spring_auth.department.service;

import com.spring_auth.department.entity.Department;
import com.spring_auth.department.repository.DepartmentRepository;
import com.spring_auth.department.reqeust.DepartmentRequest;
import com.spring_auth.department.reqeust.DepartmentResponse;
import com.spring_auth.team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Transactional
    public DepartmentResponse save(DepartmentRequest request) {
        Department resultDepartment = departmentRepository.save(request.toDepartmentEntity());
        return resultDepartment.toResponse();
    }

    public Department findById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 부서 입니다."));
    }
}

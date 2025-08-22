package com.spring_auth.department.service;

import com.spring_auth.department.entity.Department;
import com.spring_auth.department.repository.DepartmentRepository;
import com.spring_auth.department.dto.DepartmentRequest;
import com.spring_auth.department.dto.DepartmentResponse;
import java.util.List;
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

    public Department findByDeptName(String deptName) {
        return departmentRepository.findByDeptName(deptName)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 부서 입니다."));
    }

    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll().stream()
                .map(Department::toResponse)
                .toList();
    }
}

package com.spring_auth.employee.service;

import com.spring_auth.department.entity.Department;
import com.spring_auth.department.service.DepartmentService;
import com.spring_auth.employee.entity.Employee;
import com.spring_auth.employee.repository.EmployeeRepository;
import com.spring_auth.employee.reqeust.EmployeeRequest;
import com.spring_auth.employee.reqeust.EmpoyeeResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    @Transactional
    public EmpoyeeResponse save(EmployeeRequest request) {
        Department department = departmentService.findById(request.getDepartmentId());
        Employee employeeResult = employeeRepository.save(request.toEmployeeEntity(department));
        return employeeResult.toResponse();
    }

    public List<EmpoyeeResponse> findAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(Employee::toResponse)
                .toList();
    }
}

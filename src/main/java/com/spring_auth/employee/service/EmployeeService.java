package com.spring_auth.employee.service;

import com.spring_auth.department.entity.Department;
import com.spring_auth.department.repository.DepartmentRepository;
import com.spring_auth.department.service.DepartmentService;
import com.spring_auth.employee.entity.Employee;
import com.spring_auth.employee.repository.EmployeeRepository;
import com.spring_auth.employee.reqeust.EmployeeRequest;
import com.spring_auth.employee.reqeust.EmpoyeeResponse;
import com.spring_auth.employeeRole.entity.EmployeeRole;
import com.spring_auth.role.entity.Role;
import com.spring_auth.role.repository.RoleRepository;
import com.spring_auth.role.service.RoleService;
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
    private final RoleService roleService;

    @Transactional
    public EmpoyeeResponse save(EmployeeRequest request) {
        Department department = departmentService.findByDeptName(request.getDeptName());
        Role role = roleService.findByRoleName(request.getRoleName());
        Employee employee = request.toEmployeeEntity(department);

        EmployeeRole employeeRole = EmployeeRole.builder()
                .employee(employee)
                .role(role)
                .build();

        employee.addEmployeeRole(employeeRole);
        Employee employeeResult = employeeRepository.save(employee);

        return employeeResult.toResponse();
    }

    public List<EmpoyeeResponse> findAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(Employee::toResponse)
                .toList();
    }
}

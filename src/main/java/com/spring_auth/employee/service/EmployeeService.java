package com.spring_auth.employee.service;

import com.spring_auth.department.entity.Department;
import com.spring_auth.department.service.DepartmentService;
import com.spring_auth.employee.entity.Employee;
import com.spring_auth.employee.repository.EmployeeRepository;
import com.spring_auth.employee.dto.EmployeeRequest;
import com.spring_auth.employee.dto.EmpoyeeResponse;
import com.spring_auth.employeeRole.entity.EmployeeRole;
import com.spring_auth.role.entity.Role;
import com.spring_auth.role.service.RoleService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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

        EmployeeRole employeeRole = EmployeeRole.of(employee, role);

        employee.addEmployeeRole(employeeRole);
        Employee employeeResult = employeeRepository.save(employee);

        return employeeResult.toResponse();
    }

    public List<EmpoyeeResponse> findAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(Employee::toResponse)
                .toList();
    }

    //Cacheable은 데이터가 없으면 캐시에 넣고 있으면 ttl 업데이트 해준다.
    @Cacheable(cacheNames = "employee", key = "#id")
    public EmpoyeeResponse findById(Long id) {
        return employeeRepository.findByEmployeeId(id)
                .orElseThrow(() -> new NoSuchElementException("id를 찾을 수 없습니다."))
                .toResponse();
    }
}

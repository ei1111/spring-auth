package com.spring_auth.employee.controller;

import com.spring_auth.department.reqeust.DepartmentRequest;
import com.spring_auth.employee.entity.Employee;
import com.spring_auth.employee.reqeust.EmployeeRequest;
import com.spring_auth.employee.reqeust.EmpoyeeResponse;
import com.spring_auth.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/api/employees")
    public ResponseEntity<EmpoyeeResponse> save(@RequestBody EmployeeRequest request) {
        return new ResponseEntity(employeeService.save(request), HttpStatus.CREATED);
    }
}

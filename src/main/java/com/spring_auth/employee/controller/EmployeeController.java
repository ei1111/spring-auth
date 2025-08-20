package com.spring_auth.employee.controller;

import com.spring_auth.department.reqeust.DepartmentRequest;
import com.spring_auth.employee.entity.Employee;
import com.spring_auth.employee.reqeust.EmployeeRequest;
import com.spring_auth.employee.reqeust.EmpoyeeResponse;
import com.spring_auth.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "1. 회원" , description = "회원 관리 API")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    @Operation(summary = "회원 저장")
    public ResponseEntity<EmpoyeeResponse> save(@RequestBody EmployeeRequest request) {
        return new ResponseEntity(employeeService.save(request), HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    @Operation(summary = "모든 회원 조회")
    public ResponseEntity<List<EmpoyeeResponse>> employees() {
        return ResponseEntity.ok().body(employeeService.findAllEmployees());
    }
}

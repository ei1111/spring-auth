package com.spring_auth.employee.controller;

import com.spring_auth.employee.dto.EmployeeRequest;
import com.spring_auth.employee.dto.EmpoyeeResponse;
import com.spring_auth.employee.entity.Employee;
import com.spring_auth.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "1. 회원" , description = "회원 관리 API")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/admin/employees")
    @Operation(summary = "회원 저장")
    public ResponseEntity<EmpoyeeResponse> save(@RequestBody EmployeeRequest request) {
        return new ResponseEntity(employeeService.save(request), HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    @Operation(summary = "모든 회원 조회")
    public ResponseEntity<List<EmpoyeeResponse>> employees() {
        return ResponseEntity.ok().body(employeeService.findAllEmployees());
    }

    @GetMapping("/employees/{id}")
    @Operation(summary = "단건 회원 조회")
    public ResponseEntity<EmpoyeeResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(employeeService.findById(id));
    }
}

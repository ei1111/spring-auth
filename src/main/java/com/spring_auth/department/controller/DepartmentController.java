package com.spring_auth.department.controller;

import com.spring_auth.department.reqeust.DepartmentRequest;
import com.spring_auth.department.reqeust.DepartmentResponse;
import com.spring_auth.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/api/departments")
    public ResponseEntity<DepartmentResponse> save(@RequestBody DepartmentRequest request) {
        return new ResponseEntity(departmentService.save(request), HttpStatus.CREATED);
    }
}

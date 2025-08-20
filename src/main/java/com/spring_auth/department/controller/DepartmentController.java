package com.spring_auth.department.controller;

import com.spring_auth.department.reqeust.DepartmentRequest;
import com.spring_auth.department.reqeust.DepartmentResponse;
import com.spring_auth.department.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.http.HttpResponse;
import java.util.List;
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
@Tag(name = "2. 부서" , description = "부서 관리 API")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/departments")
    @Operation(summary = "부서 저장")
    public ResponseEntity<DepartmentResponse> save(@RequestBody DepartmentRequest request) {
        return new ResponseEntity(departmentService.save(request), HttpStatus.CREATED);
    }

    @GetMapping("/departments")
    @Operation(summary = "모든 부서 조회")
    public ResponseEntity<List<DepartmentResponse>> findAll() {
        return ResponseEntity.ok().body(departmentService.findAll());
    }
}

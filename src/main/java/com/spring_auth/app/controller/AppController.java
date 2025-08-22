package com.spring_auth.app.controller;

import com.spring_auth.app.request.AppRequest;
import com.spring_auth.app.request.AppResponse;
import com.spring_auth.app.service.AppService;
import com.spring_auth.employee.entity.Employee;
import com.spring_auth.employee.reqeust.EmployeeRequest;
import com.spring_auth.employee.reqeust.EmpoyeeResponse;
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
@Tag(name = "5. 기능" , description = "기능 관리 API")
public class AppController {
    private final AppService appService;

    @GetMapping("/apps")
    @Operation(summary = "앱 기능 모두 조회")
    public ResponseEntity<List<AppResponse>> findAllApps() {
        return ResponseEntity.ok().body(appService.findAllApps());
    }

    @PostMapping("/apps")
    @Operation(summary = "앱 기능 저장")
    public ResponseEntity<AppResponse> save(@RequestBody AppRequest request) {
        return new ResponseEntity(appService.save(request), HttpStatus.CREATED);
    }
}

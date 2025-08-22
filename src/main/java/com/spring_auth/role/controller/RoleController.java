package com.spring_auth.role.controller;

import com.spring_auth.role.dto.RoleRequest;
import com.spring_auth.role.dto.RoleResponse;
import com.spring_auth.role.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "3. 직책" , description = "직책 관리 API")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/roles")
    @Operation(summary = "직책 저장")
    public ResponseEntity<RoleResponse> save(@RequestBody RoleRequest roleRequest) {
        return new ResponseEntity(roleService.save(roleRequest), HttpStatus.CREATED);
    }
}

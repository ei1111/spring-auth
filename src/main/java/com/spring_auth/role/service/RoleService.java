package com.spring_auth.role.service;

import com.spring_auth.role.entity.Role;
import com.spring_auth.role.repository.RoleRepository;
import com.spring_auth.role.dto.RoleRequest;
import com.spring_auth.role.dto.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 직책 입니다."));
    }

    @Transactional
    public RoleResponse save(RoleRequest roleRequest) {
        if (roleRepository.existsRoleByRoleName(roleRequest.getRoleName())) {
            throw new IllegalArgumentException("존재 하는 직책 입니다.");
        }

        return roleRepository.save(roleRequest.toRoleEntity()).toResponse();
    }
}

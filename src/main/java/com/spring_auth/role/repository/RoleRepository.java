package com.spring_auth.role.repository;

import com.spring_auth.role.entity.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
    boolean existsRoleByRoleName(String roleName);
}

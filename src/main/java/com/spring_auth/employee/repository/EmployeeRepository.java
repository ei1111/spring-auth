package com.spring_auth.employee.repository;

import com.spring_auth.employee.dto.EmpoyeeResponse;
import com.spring_auth.employee.entity.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsEmployeeByKakoNickName(String kakoNickName);

    @EntityGraph(attributePaths = {"employeeRoles", "employeeRoles.role"})
    Optional<Employee> findByKakoNickName(String kakoNickName);

    Optional<Employee> findByEmployeeId(Long employeeId);
}

package com.spring_auth.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring_auth.employee.entity.Employee;
import com.spring_auth.role.entity.Role;
import io.jsonwebtoken.Claims;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtUtilTest {

    private Employee employee;
    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setUp() {
        String testNick = "joan doe";

     /*   Set<Role> roles = Set.of(
                Role.builder()
                        .roleId(1L)
                        .name("인사팀")
                        .build(),

                Role.builder()
                        .roleId(2L)
                        .name("it팀")
                        .build()
        );

        employee = Employee.builder()
                .kakoNickName(testNick)
                .roles(roles)
                .build();*/

    }

    @AfterEach
    public void reset() {
    }


    @DisplayName("토큰의 이름을 확인할 수 있다")
    @Test
    void 토큰의_이름을_확인할_수_있다() throws Exception {
        //given
        //when
        String token = JwtUtil.createToken(employee);
        Claims claims = JwtUtil.parseToken(token);

        //then
        assertEquals("joan doe", claims.get("nickName"));
    }


    @DisplayName("role을 확인할 수 있다")
    @Test
    void role을_확인할_수_있다() throws Exception {
        //given
        //when
/*        String token = JwtUtil.createToken(employee);
        Set<String> roles = employee.getRoleNameList();
        List<String> claimsRoles =  JwtUtil.parseToken(token).get("roles");

        //then
        assertEquals(roles, claimsRoles);*/
    }
}
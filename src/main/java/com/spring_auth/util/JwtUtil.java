package com.spring_auth.util;

import com.spring_auth.employee.entity.Employee;

import com.spring_auth.role.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.crypto.SecretKey;

public class JwtUtil {

    private static final String SECRET = "your-256-bit-secret-your-256-bit-secret-!"; // 최소 256비트
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            SECRET.getBytes(StandardCharsets.UTF_8));
    private static final long expirationTimeInMills = 1000 * 60 * 60;


    public static String createToken(Employee employee) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTimeInMills); // 1시간 유효

        return Jwts.builder()
                .setSubject(String.valueOf(employee.getEmployeeId()))
                .addClaims(
                        Map.of(
                                "nickName", employee.getKakoNickName()
                                , "roles", employee.getRoleNameSet()
                        )
                )
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY) // HS256 Key
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}

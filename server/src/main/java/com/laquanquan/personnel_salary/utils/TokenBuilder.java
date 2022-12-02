package com.laquanquan.personnel_salary.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @author lqq
 */
public class TokenBuilder {
    private static final Key PRIVATE_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long HOUR = 1000 * 60 * 60;

    public static String build(Map<String, Object> payload, int expireHours) {
        return Jwts.builder()
                .signWith(PRIVATE_KEY)
                .setIssuer("zuobiaoxi")
                .addClaims(payload)
                .setExpiration(new Date(System.currentTimeMillis() + (HOUR * expireHours)))
                .setSubject("salary:token")
                .compact();
    }

    public static Map<String, Object> parse(String token) {
        return (Map<String, Object>) Jwts.parserBuilder()
                .requireIssuer("zuobiaoxi")
                .setSigningKey(PRIVATE_KEY)
                .requireSubject("salary:token")
                .build().parse(token).getBody();
    }
}

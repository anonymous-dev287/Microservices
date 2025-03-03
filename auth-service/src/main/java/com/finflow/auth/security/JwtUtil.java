//package com.finflow.auth.security;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtUtil {
//
//    private static final String SECRET_KEY = "bdzzZ8MxRHDR7DPYuKhcLteQZYwbQrHUcV4nYE65ark="; // Use the same key in API Gateway
//    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 1 day expiration
//                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder()
//                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
//                .build()
//                .parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false; // Invalid token
//        }
//    }
//
//    public String extractUsername(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//}

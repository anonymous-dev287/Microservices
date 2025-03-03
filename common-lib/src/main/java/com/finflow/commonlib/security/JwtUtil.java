package com.finflow.commonlib.security;

//import java.security.Key;
//import java.util.Date;
//
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import io.jsonwebtoken.security.SignatureException;
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public class JwtUtil {
//    private static final String SECRET_KEY = "bdzzZ8MxRHDR7DPYuKhcLteQZYwbQrHUcV4nYE65ark="; // Ensure at least 256-bit key
//    private static final long EXPIRATION_TIME = 86400000; // 1 day
//
//    private final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
//    
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(key, SignatureAlgorithm.HS256) // ✅ FIXED: Correct HS256 usage
//                .compact();
//    }
//
//    public String extractUsername(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key) // ✅ FIXED: `setSigningKey()` replaces `verifyWith()`
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(key) // ✅ FIXED: `setSigningKey()` replaces `verifyWith()`
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//            return claims.getExpiration().after(new Date());
//        }catch (ExpiredJwtException e) {
//            System.out.println("JWT expired");
//            return false;
//        } catch (SignatureException e) {
//            System.out.println("Invalid JWT signature");
//            return false;
//        }
//    }
//    
////    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
////        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
////        return claimsResolver.apply(claims);
////    }
//}

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "bdzzZ8MxRHDR7DPYuKhcLteQZYwbQrHUcV4nYE65ark="; // Use the same key in API Gateway
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 1 day expiration
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // Invalid token
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

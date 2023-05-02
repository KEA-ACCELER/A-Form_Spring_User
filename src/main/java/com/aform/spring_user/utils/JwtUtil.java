package com.aform.spring_user.utils;

import java.util.Date;

import io.jsonwebtoken.*;

public class JwtUtil {
    
   public static String createJwt(String userName, String secretKey, Long expireMs){
        Claims claims = Jwts.claims();
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
   } 
}

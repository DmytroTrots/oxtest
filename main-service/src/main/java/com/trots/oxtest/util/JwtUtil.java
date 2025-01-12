package com.trots.oxtest.util;

import com.trots.oxtest.model.entity.UserEntity;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwtKey}")
    private String SECRET_KEY;
    private static final long EXPIRATION_TIME = 3600000;

    public String generateToken(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getEmail());
        claims.put("roles", user.getRoles());

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return (String) Jwts.parserBuilder().setSigningKey(SECRET_KEY).build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("username");
        } catch (ExpiredJwtException e) {
            return (String) e.getClaims().get("username");
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            Date expiration = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String refreshToken(UserEntity user) {
            return generateToken(user);
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}

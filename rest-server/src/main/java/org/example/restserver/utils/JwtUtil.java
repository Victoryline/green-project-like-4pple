package org.example.restserver.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key;
    private final long expiration;
    private final String issuer;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration,
            @Value("${jwt.issuer}") String issuer) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
        this.issuer = issuer;
    }

    public String createToken(String username, String name, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("name", name)
                .claim("role", role)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            if (!issuer.equals(claims.getIssuer())) {
                throw new JwtException("Invalid issuer");
            }
            return claims;
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    public String getUsername(String token) {
        Claims claims = validateToken(token);
        return claims.getSubject();
    }

    public String getUserRole(String token) {
        Claims claims = validateToken(token);
        return claims.get("role", String.class);
    }

    public String getName(String token) {
        Claims claims = validateToken(token);
        return claims.get("name", String.class);
    }


}

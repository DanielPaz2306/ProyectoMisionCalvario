package com.mision.calvario.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generarToken(String username, String rol, Long pastorId, Long distritoId) {
        return Jwts.builder()
                .subject(username)
                .claim("rol", rol)
                .claim("pastorId", pastorId)
                .claim("distritoId", distritoId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public String extraerUsername(String token){
        return extraerClaims(token).getSubject();
    }

    public String extraerRol(String token){
        return extraerClaims(token).get("rol", String.class);
    }

    public boolean validarToken(String token, String username){
        return extraerUsername(token).equals(username) && !estaExpirado(token);
    }

    private boolean estaExpirado(String token){
        return extraerClaims(token).getExpiration().before(new Date());
    }

    private Claims extraerClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}

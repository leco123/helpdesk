package com.carvalho.helpdesk.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Classe usada para aplicar regrar úteis da JWT - Jeison Web Token
 */
@Component
public class JWTUtil {

    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Método para gerar os tokens
     * @param email
     * @return String
     */
   public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Informações do token
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes(StandardCharsets.UTF_8))
                .compact();
   }

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida o token
     * @param token
     * @return Claims
     */
    private Claims getClaims(String token) {
       try {
           return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
       } catch (Exception e) {
        throw new RuntimeException(e);
       }
    }

    /**
     * Retorna nome Usuário
     * @param token
     * @return String
     */
    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }
}

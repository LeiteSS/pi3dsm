package com.fatec.fatecflix.security;

import java.util.Date;

import com.fatec.fatecflix.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${security.config.EXPIRATION}")
    private String expiration;

    @Value("${security.config.key}")
    private String secret;

    public String getToken(Authentication authentication) {
        User logged = (User) authentication.getPrincipal();
        Date today = new Date();
        Date expDate = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder().setIssuer("API fatecflix").setSubject(logged.getId().toString())
                .setIssuedAt(today).setExpiration(expDate).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}

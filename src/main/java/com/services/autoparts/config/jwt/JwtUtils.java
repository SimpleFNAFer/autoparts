package com.services.autoparts.config.jwt;

import com.services.autoparts.model.user.UserDetailsImplemented;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtExpirationMs}")
    private Integer jwtExpirationMs;


    public String generateJwtToken(Authentication authentication) {
        UserDetailsImplemented userPrincipal = (UserDetailsImplemented) authentication.getPrincipal();

        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public Boolean validateJwtToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        }
        catch (MalformedJwtException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public String getUsernameFromJwtToken(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }
}

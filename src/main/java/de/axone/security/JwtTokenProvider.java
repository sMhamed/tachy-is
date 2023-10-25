package de.axone.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();

        Date expirationDate = new Date(now.getTime() + jwtExpirationInMs);

        return JWT.create()
                .withSubject(Long.toString(userPrincipal.getId()))
                .withClaim("tachy_username", userPrincipal.getUsername())
                .withClaim("tachy_email", userPrincipal.getEmail())
                .withClaim("tachy_roles", userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .withIssuedAt(new Date())
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC512(jwtSecret))
                .build()
                .verify(token);
    }

    public Long getUserIdFromJWT(String token) {
        return Long.parseLong(decode(token).getSubject());
    }

    public String getUsernameFromJWT(String token) {
        return decode(token).getClaim("tachy_username").toString();
    }

    public String getEmailFromJWT(String token) {
        return decode(token).getClaim("tachy_email").toString();
    }

    public String getRolesFromJWT(String token) {
        return decode(token).getClaim("tachy_roles").toString();
    }

}

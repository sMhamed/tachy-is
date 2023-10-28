package de.axone.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import de.axone.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return JWT.create()
                .withSubject(Long.toString(userPrincipal.getId()))
                .withClaim("tachy_username", userPrincipal.getUsername())
                .withClaim("tachy_email", userPrincipal.getEmail())
                .withClaim("tachy_roles", userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .withIssuedAt(new Date())
                .withExpiresAt(getExpirationDate())
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public String generateToken(User user) {

        return JWT.create()
                .withSubject(Long.toString(user.getId()))
                .withClaim("tachy_username", user.getUsername())
                .withClaim("tachy_email", user.getEmail())
                .withClaim("tachy_roles", user.getRoles().stream().map(role -> role.getName().toString()).collect(Collectors.joining(",")))
                .withIssuedAt(new Date())
                .withExpiresAt(getExpirationDate())
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

    private Date getExpirationDate() {
        Date now = new Date();

        return new Date(now.getTime() + jwtExpirationInMs);
    }
}

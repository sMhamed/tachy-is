package de.axone.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {

    private String accessToken;

    private String token;

    private String tokenType = "Bearer";

    private Long userId;

    private String emailClaim;

    private String usernameClaim;

    private String roles;

    public JwtAuthenticationResponse(String accessToken, String token, Long userId, String emailClaim, String usernameClaim, String roles) {
        this.accessToken = accessToken;
        this.token = token;
        this.userId = userId;
        this.emailClaim = emailClaim;
        this.usernameClaim = usernameClaim;
        this.roles = roles;
    }
}

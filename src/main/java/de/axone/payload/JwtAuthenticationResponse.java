package de.axone.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {

    private String accessToken;

    private String tokenType = "Bearer";

    private Long userId;

    private String emailClaim;

    private String usernameClaim;

    public JwtAuthenticationResponse(String accessToken, Long userId, String emailClaim, String usernameClaim) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.emailClaim = emailClaim;
        this.usernameClaim = usernameClaim;
    }
}
